package styleai.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import kotlinx.coroutines.flow.Flow
import styleai.core.models.local.StadiumEntity

@Dao
interface StadiumDao {
    @Query("SELECT *  from StadiumEntity")
    fun getImages(): Flow<List<StadiumEntity>>

    @Query("DELETE FROM StadiumEntity WHERE id NOT in (:imageIds)")
    suspend fun deleteExceptIds(imageIds: List<String>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllSync(imageEntities: List<StadiumEntity>)

    @Transaction
    suspend fun replaceAllSync(images: List<StadiumEntity>) {
        deleteExceptIds(images.map { it.id })
        saveAllSync(images)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(image: StadiumEntity)

    @Query("SELECT * FROM StadiumEntity WHERE id = :imageId")
    fun getImageById(imageId: Int): Flow<StadiumEntity>

}