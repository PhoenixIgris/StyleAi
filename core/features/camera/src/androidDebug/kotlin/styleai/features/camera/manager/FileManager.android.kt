package styleai.features.camera.manager

import java.io.File

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
            val netUrl = java.net.URL(url)
            val imageData = netUrl.readBytes()

            // Create file and save data
            val file = File(dirName, fileName)
            file.writeBytes(imageData)

            var durationInSeconds: Long? = null
            if (isVideo) {
                val retriever = android.media.MediaMetadataRetriever()
                try {
                    retriever.setDataSource(file.absolutePath)
                    val duration =
                        retriever.extractMetadata(android.media.MediaMetadataRetriever.METADATA_KEY_DURATION)
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