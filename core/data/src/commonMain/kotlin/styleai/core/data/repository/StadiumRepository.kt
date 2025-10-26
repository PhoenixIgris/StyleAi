package styleai.core.data.repository

import com.github.kittinunf.result.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.withContext
import styleai.core.common.manager.FileManager
import styleai.core.data.utils.toDomain
import styleai.core.data.utils.toResult
import styleai.core.database.dao.StadiumDao
import styleai.core.database.local.StadiumEntity
import styleai.core.models.ApiCallFailure
import styleai.core.network.StyleAiRemoteApi


class StadiumRepository(
    private val stadiumDao: StadiumDao,
    private val apiInterface: StyleAiRemoteApi,
) {

    suspend fun saveImages(
        imageDir: String, imageList: List<StadiumEntity>
    ): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val savedImageResponse = imageList.map { stadiumEntity ->
                    async {
                        val localImagePath = FileManager().writeFileFromUrl(
                            dirName = imageDir,
                            url = stadiumEntity.imageUrl,
                            fileName = stadiumEntity.id,
                            isVideo = false
                        )
                        if (localImagePath.isFailure) {
                            throw localImagePath.exceptionOrNull()
                                ?: Exception("Failed to download image with ID: ${stadiumEntity.id}")
                        }
                        localImagePath.getOrNull()?.let {
                            stadiumEntity.localImagePath = it.first
                        }
                        stadiumEntity
                    }
                }.awaitAll()
                stadiumDao.replaceAllSync(savedImageResponse)
                Result.success(Unit)

            } catch (exception: Exception) {
                Result.failure(exception)
            }
        }
    }


    suspend fun syncImages(imageDownloadDir: String): Result<Unit> {
        return when (val result = refreshStadiumData()) {
            is com.github.kittinunf.result.Result.Failure -> {
                Result.failure(Exception(result.error.message))
            }

            is com.github.kittinunf.result.Result.Success -> {
                if (getStadiumDataList().firstOrNull()?.sortedBy { it.id }
                        .hashCode() == result.value.sortedBy {
                        it.id
                    }.hashCode()) return Result.success(Unit)
                val savedImages = saveImages(imageDownloadDir, result.value)
                return if (!savedImages.isSuccess) {
                    Result.failure(Exception("Failed to save images"))
                } else {
                    Result.success(Unit)
                }
            }
        }
    }


    fun getStadiumDataList(): Flow<List<StadiumEntity>> {
        return stadiumDao.getImages()
    }


     suspend fun refreshStadiumData(): com.github.kittinunf.result.Result<List<StadiumEntity>, ApiCallFailure> {
        return apiInterface.getStadiumData().toResult().map {
            listOf<StadiumEntity>((it ?: "NO DATA").toDomain())
        }
    }


}