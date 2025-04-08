package com.o9tech.prankcall.utils

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import androidx.core.net.toUri

object AppConstant {
    const val Dummy_ID = ""

    fun getVideoPathFromAssets(context: Context, videoFileName: String): String {
        val assetManager = context.assets
        val inputStream = assetManager.open(videoFileName)
        val file = File(context.cacheDir, videoFileName)
        val outputStream = FileOutputStream(file)

        // Copy the video file from assets to cache directory
        inputStream.copyTo(outputStream)

        return file.absolutePath
    }






    fun saveVideoToStorage(context: Context, videoPath: String): File {
        // Get the input stream of the video
        val inputStream = context.contentResolver.openInputStream(videoPath.toUri())
        val file = File(context.filesDir, "uploaded_video.mp4")  // You can customize the filename
        val outputStream = FileOutputStream(file)
        inputStream?.copyTo(outputStream)
        inputStream?.close()
        outputStream.close()

        return file
    }

    fun getRealPathFromURI(context: Context, uri: Uri): String {
        var cursor: Cursor? = null
        try {
            val projection = arrayOf(MediaStore.Video.Media.DATA)
            cursor = context.contentResolver.query(uri, projection, null, null, null)
            val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Video.Media.DATA)
            cursor?.moveToFirst()
            return cursor?.getString(columnIndex!!) ?: ""
        } finally {
            cursor?.close()
        }
    }



//    val pickVideo = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
//        uri?.let {
//            // Once the video is selected, get its path and save it
//            val videoPath = getRealPathFromURI(context, it)
//            // Save the video to internal storage or cache directory
//            saveVideoToStorage(videoPath)
//        }
//    }


}