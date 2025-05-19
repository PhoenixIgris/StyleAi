package styleai.features.camera.manager

expect class FileManager() {
    fun writeFileFromUrl(
        dirName: String, url: String, fileName: String, isVideo: Boolean
    ): Result<Pair<String, Long?>>

    fun getFilePath(dirName: String, fileName: String): String?

}