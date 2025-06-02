package com.o9tech.prankcall.Screen.Videocalling


import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.compose.BackHandler
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.navigation.NavHostController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.o9tech.prankcall.AppNavigation.Routes
import com.o9tech.prankcall.Screen.Apputils.CameraPreviewBox
import com.o9tech.prankcall.viewModel.MainViewModel
import java.io.File
import java.io.FileOutputStream


@OptIn(UnstableApi::class)
@Composable
fun FakeVideoCallScreen(
    mainViewModel: MainViewModel,
    videoPath: String,
    navController: NavHostController,
    profileImage: String,
) {
    val context = LocalContext.current


    fun copyAssetToCache(context: Context, fileName: String): File {
        val cacheFile = File(context.cacheDir, fileName)
        if (!cacheFile.exists()) {
            context.assets.open(fileName).use { input ->
                FileOutputStream(cacheFile).use { output ->
                    input.copyTo(output)
                }
            }
        }
        return cacheFile
    }


//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            val path = copyAssetToCache(context, videoPath)
////            copyAssetToCache(context, "call2.mp4")
////            copyAssetToCache(context, "prank.mp4")
//            Log.d("videopathisthis", "FakeVideoCallScreen: ${path}")
////            val uri = Uri.fromFile(File(videoPath))
//            val uri = Uri.fromFile(path)
//
//            val mediaItem = MediaItem.fromUri(uri)
//            setMediaItem(mediaItem)
//            repeatMode = Player.REPEAT_MODE_ONE
//            prepare()
//            playWhenReady = true
//        }
//    }



//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            val file = File(videoPath)
//            if (file.exists()) {
//                val uri = Uri.fromFile(file)
//                val mediaItem = MediaItem.fromUri(uri)
//                setMediaItem(mediaItem)
//                prepare()
//                playWhenReady = true
//            } else {
//                Log.e("Video", "Video file not found at $videoPath")
//            }
//        }
//    }




    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            repeatMode = Player.REPEAT_MODE_ALL
            val file = File(videoPath)
            if (file.exists()) {
                val uri = Uri.fromFile(file)
                val mediaItem = MediaItem.fromUri(uri)
                setMediaItem(mediaItem)
                prepare()
                playWhenReady = true
            } else {
                Log.e("Video", "File not found at $videoPath, loading fallback from assets")

                try {
                    val fallbackFile = copyAssetToCache(context, videoPath)
                    val uri = Uri.fromFile(fallbackFile)
                    val mediaItem = MediaItem.fromUri(uri)
                    setMediaItem(mediaItem)
                    prepare()
                    playWhenReady = true
                } catch (e: Exception) {
                    Log.e("VideoError", "Failed to load fallback asset video: ${e.message}")
                    e.printStackTrace()
                }
            }
        }
    }



    BackHandler {
        exoPlayer.release()
        navController.popBackStack()
        val encodedPicPath = Uri.encode(profileImage)
        navController.navigate("CallEndedScreen/$encodedPicPath")
    }



    Scaffold {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        player = exoPlayer
                        useController = false
                        layoutParams = FrameLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT
                        )
                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    }

                },
                )
            Box(
                modifier = Modifier
                    .padding(top = 36.dp, end = 16.dp)
                    .align(Alignment.TopEnd)
            ) {
                CameraPreviewBox()
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(vertical = 16.dp, horizontal = 16.dp)
                        .background(
                            color = Color.Black.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(8.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.Black, shape = CircleShape)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.DateRange,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }


                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.Black, shape = CircleShape)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.AddCircle,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.Black, shape = CircleShape)
                                .clickable { },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .background(Color.Red, shape = CircleShape)
                                .clickable {
                                    exoPlayer.release()
                                    navController.popBackStack()
                                    val encodedPicPath = Uri.encode(profileImage)
                                    navController.navigate("CallEndedScreen/$encodedPicPath")

                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Call,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                }
            }
        }
    }

}