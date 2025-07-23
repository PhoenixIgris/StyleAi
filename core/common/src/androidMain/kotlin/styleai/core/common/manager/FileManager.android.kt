package styleai.core.common.manager

import android.media.MediaMetadataRetriever
import java.io.File
import java.net.URL
import kotlin.io.deleteRecursively
import kotlin.io.readBytes
import kotlin.io.writeBytes
import kotlin.text.toLongOrNull
import kotlin.to

actual class FileManager {

    private fun createDir(dirName: String) {
        val file = File(dirName)
        if (!file.exists()) {
            file.mkdir()
        } else {
            file.deleteRecursively()
            file.mkdir()
        }
    }

    actual fun writeFileFromUrl(
        dirName: String,
        url: String,
        fileName: String,
        isVideo: Boolean
    ): Result<Pair<String, Long?>> {

        try {
            createDir(dirName)
            val netUrl = URL(url)
            val imageData = netUrl.readBytes()

            // Create file and save data
            val file = File(dirName, fileName)
            file.writeBytes(imageData)

            var durationInSeconds: Long? = null
            if (isVideo) {
                val retriever = MediaMetadataRetriever()
                try {
                    retriever.setDataSource(file.absolutePath)
                    val duration =
                        retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
                            ?.toLongOrNull() ?: 0L
                    durationInSeconds = duration / 1000 // Convert to seconds
                } catch (e: Exception) {
                    e.printStackTrace()
                } finally {
                    retriever.release()
                }
            }
            return Result.success(file.absolutePath to durationInSeconds)
        } catch (e: Exception) {
            e.printStackTrace()
            return Result.failure(Exception("Failed to save file: ${e.message}"))
        }
    }

    actual fun getFilePath(dirName: String, fileName: String): String? {
        return fileName
    }

}