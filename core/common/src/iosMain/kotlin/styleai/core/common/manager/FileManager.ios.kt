package styleai.core.common.manager

import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVURLAsset
import platform.CoreMedia.CMTimeGetSeconds
import platform.Foundation.NSData
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask
import platform.Foundation.dataWithContentsOfURL
import platform.Foundation.writeToURL
import kotlin.collections.last
import kotlin.getOrElse
import kotlin.text.contains
import kotlin.to

@OptIn(ExperimentalForeignApi::class)
actual class FileManager {
    private val fileManager = NSFileManager.defaultManager
    private val documentsDir =
        fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask).last() as NSURL

    private fun createDir(dirName: String): Result<NSURL> {
        val dir = documentsDir.URLByAppendingPathComponent(dirName, true)
        val imageDirPath = dir?.path
            ?: return Result.failure(Exception("Empty directory: $dir ${dir?.absoluteString}"))

        if (!fileManager.fileExistsAtPath(imageDirPath)) {
            val success = fileManager.createDirectoryAtPath(
                imageDirPath,
                withIntermediateDirectories = true,
                attributes = null,
                error = null
            )
            if (!success) {
                return Result.failure(Exception("Failed to create directory: $imageDirPath"))
            } else {
                println("Created directory: $imageDirPath")
            }
        }
        return Result.success(dir)
    }

    actual fun writeFileFromUrl(
        dirName: String, url: String, fileName: String, isVideo: Boolean
    ): Result<Pair<String, Long?>> {
        val dir = createDir(dirName).getOrElse {
            return Result.failure(it)
        }

        val nsUrl = NSURL.URLWithString(url)
            ?: return Result.failure(Exception("Invalid URL: $url"))
        val fileExtension = nsUrl.pathExtension ?: ""
        val finalFileName = if (fileName.contains(".")) fileName else "$fileName.$fileExtension"
        val filePathUrl =
            dir.URLByAppendingPathComponent(finalFileName, false) ?: return Result.failure(
                Exception("Empty file: $fileExtension ${finalFileName}")
            )


        val data = NSData.dataWithContentsOfURL(nsUrl)
        if (data == null || data.length.toLong() == 0L) {
            return Result.failure(Exception("Failed to download file or file is empty: $url"))
        }


        // Save file

        val success = data.writeToURL(filePathUrl, true)
        if (!success) {
            return Result.failure(Exception("Failed to save file at: ${data.length} ${dir.absoluteString}"))
        }

        val result = (finalFileName) to (if (isVideo) getDuration(filePathUrl) else 0L)
        println("File result  $result")
        return Result.success(result)


    }

    private fun getDuration(fileUrl: NSURL): Long {
        val asset = AVURLAsset.URLAssetWithURL(fileUrl, null)
        val duration = asset.duration
        return CMTimeGetSeconds(duration).toLong()
    }


    fun getFileNSURL(dir: String, fileName: String): NSURL {
        val fileManager = NSFileManager.defaultManager
        val documentsDir =
            fileManager.URLsForDirectory(NSDocumentDirectory, NSUserDomainMask).last() as NSURL
        val dirNsUrl = documentsDir.URLByAppendingPathComponent(dir, true)
        val filePathUrl =
            dirNsUrl?.URLByAppendingPathComponent(fileName, false)
        return filePathUrl
            ?: throw Exception("Could not find file at ${filePathUrl?.absoluteString}")
    }

    actual fun getFilePath(dirName: String, fileName: String): String? {
        return getFileNSURL(dirName, fileName).absoluteString
    }


}