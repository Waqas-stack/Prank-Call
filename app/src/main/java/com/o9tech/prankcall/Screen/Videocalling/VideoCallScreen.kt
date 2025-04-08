package com.o9tech.prankcall.Screen.Videocalling



import android.net.Uri
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


@OptIn(UnstableApi::class)
@Preview(showBackground = true)
@Composable
fun FakeVideoCallScreen(
    mainViewModel: MainViewModel,
    videoPath: String,
    navController: NavHostController,
    profileImage: String
) {
    val context = LocalContext.current

//    val videos by mainViewModel.videoList.collectAsState()
//
//    LaunchedEffect(Unit) {
//        Log.d("vieosarrived", "FakeVideoCallScreen: ${videoPath}")
//        mainViewModel.insertAssetVideos()
//    }


    // ExoPlayer Setup
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            val videoUri = Uri.parse("android.resource://${context.packageName}/${R.raw.prank}") // Add a sample video in raw folder
//            setMediaItem(MediaItem.fromUri(videoUri))
//            prepare()
//            playWhenReady = true
//        }
//    }

//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            val videoUri = Uri.parse("android.resource://${context.packageName}/${R.raw.prank}")
//            setMediaItem(MediaItem.fromUri(videoUri))
//            repeatMode = Player.REPEAT_MODE_ONE // 🔥 Loop Video Automatically
//            prepare()
//            playWhenReady = true
//        }
//    }
//    if(videos.isNotEmpty()) {
//        val exoPlayer = remember(videos.size) {
//            ExoPlayer.Builder(context).build().apply {
//                val uri = Uri.fromFile(File(videos[1].videoPath)) // Convert video path to Uri
//                val mediaItem = MediaItem.fromUri(uri)
//                setMediaItem(mediaItem)
//                repeatMode = Player.REPEAT_MODE_ONE // Loop video automatically
//                prepare()
//                playWhenReady = true
//            }
//        }

    val systemUiController = rememberSystemUiController()
//    systemUiController.setSystemBarsColor(
//        color = Color.Green, // status bar color
//        darkIcons = true // status bar icons will be dark
//    )

//    systemUiController.setNavigationBarColor(
//
//        color = Color.Black, // navigation bar color
//        darkIcons = false // navigation bar icons will be light
//    )


//    val navigationBarColor = Color.Blue // Customize with the desired color
//
//    systemUiController.setNavigationBarColor(
//        color = navigationBarColor,  // Set the color
//        darkIcons = false,  // Set icons to dark or light (false means dark icons)
//        navigationBarContrastEnforced = false, // Enforce contrast
//        transformColorForLightContent = { color -> color.copy(alpha = 0.7f) } // Custom transformation
//    )
//    systemUiController.isNavigationBarContrastEnforced=false



    val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                val uri = Uri.fromFile(File(videoPath))
                val mediaItem = MediaItem.fromUri(uri)
                setMediaItem(mediaItem)
                repeatMode = Player.REPEAT_MODE_ONE // 🔁 Loop video automatically
                prepare()
                playWhenReady = true
            }
        }


    BackHandler {
        // Customize the behavior on back press
        // For example, you could change the system UI or show a confirmation dialog
        println("Back button pressed!")
        exoPlayer.release()
        navController.popBackStack()
        val encodedPicPath = Uri.encode(profileImage)
        navController.navigate("CallEndedScreen/$encodedPicPath")
//        navController.navigate(Routes.CallEndedScreen)
//        Log.d("onbackpressed", "FakeVideoCallScreen: ${videoPath}")


        // You could do a custom action here or navigate, e.g.:
        // navController.popBackStack() // if using navigation
    }



    Scaffold {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                // Fake Video Call (Prerecorded Video)
//        AndroidView(
//            factory = { PlayerView(context).apply { player = exoPlayer } },
//            modifier = Modifier.fillMaxSize()
//        )
                AndroidView(
                    factory = {
                        PlayerView(context).apply {
                            player = exoPlayer
                            useController = false // 🔥 Hide Video Controls
                            layoutParams = FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                            resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                        }

                    },
//                modifier = Modifier.fillMaxSize()
                )


//            AndroidView(
//                factory = {
//                    PlayerView(context).apply {
//                        player = exoPlayer
//                        useController = false // Hide video controls
//                        layoutParams = FrameLayout.LayoutParams(
//                            ViewGroup.LayoutParams.MATCH_PARENT,
//                            ViewGroup.LayoutParams.MATCH_PARENT
//                        )
//                        resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
//                    }
//                },
//                modifier = Modifier.fillMaxSize() // Adjust size as needed
//            )


                // User's Small Camera Feed (Static Image)
                Box(
//            modifier = Modifier
//                .size(100.dp)
//                .clip(CircleShape)
//                .background(Color.Black)
//                .align(Alignment.BottomEnd)
//                .padding(16.dp)

                    modifier = Modifier
                        .padding(top = 36.dp, end = 16.dp)
                        .align(Alignment.TopEnd)
                ) {
                    CameraPreviewBox()
//            Image(
//                painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with actual user image
//                contentDescription = "User's Camera",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier.fillMaxSize()
//            )
                }

                // Call Controls
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(Alignment.BottomCenter)
//                    .padding(bottom = 16.dp)
//            ) {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .align(Alignment.BottomCenter),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    // End Call Button
//                    Button(
//                        onClick = { exoPlayer.release() }, // Stop video on end call
//                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
//                        modifier = Modifier.width(80.dp),
//                        shape = RoundedCornerShape(5.dp)
//                    ) {
//                        Text("End", color = Color.White)
//                    }
//
//                    // Accept Call Button (For UI Simulation)
//                    Button(
//                        onClick = { /* Simulate Answering the Call */ },
//                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
//                        modifier = Modifier,
//                        shape = RoundedCornerShape(5.dp)
//                    ) {
//                        Text("Accept", fontSize = 12.sp, color = Color.White)
//                    }
//                }
//            }

                ///this perfect code
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(Alignment.BottomCenter)
//                    .padding(vertical = 16.dp, horizontal = 16.dp)
//                    .background(
//                        color = Color.Black.copy(alpha = 0.5f), // 🔥 Overlay background with transparency
//                        shape = RoundedCornerShape(20.dp,) // 🔥 Rounded top corners
//                    )
//                    .padding(16.dp) // Add padding inside
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    // ❌ End Call Button
//                    Button(
//                        onClick = { exoPlayer.release() }, // Stop video on end call
//                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red),
//                        modifier = Modifier.width(80.dp),
//                        shape = RoundedCornerShape(10.dp) // Rounded button shape
//                    ) {
//                        Text("End", color = Color.White)
//                    }
//
//                    // ✅ Accept Call Button
//                    Button(
//                        onClick = { /* Simulate Answering the Call */ },
//                        colors = ButtonDefaults.buttonColors(containerColor = Color.Green),
//                        modifier = Modifier.width(80.dp),
//                        shape = RoundedCornerShape(10.dp) // Rounded button shape
//                    ) {
//                        Text("Accept", fontSize = 12.sp, color = Color.White)
//                    }
//                }
//            }

                ///this is end


                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // 🔹 Semi-Transparent Bottom Overlay Box with Rounded Corners
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(vertical = 16.dp, horizontal = 16.dp)
                            .background(
                                color = Color.Black.copy(alpha = 0.5f), // 🔥 Overlay background with transparency
                                shape = RoundedCornerShape(10.dp) // 🔥 Rounded top corners
                            )
                            .padding(8.dp) // Add padding inside
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            // 🎥 Video Toggle Button
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(Color.Black, shape = CircleShape)
                                    .clickable { /* Toggle Video */ },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
//                                painter = painterResource(id = R.drawable.ic_video), // Replace with actual video icon
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            // 🎤 Mic Toggle Button
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(Color.Black, shape = CircleShape)
                                    .clickable { /* Toggle Mic */ },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
//                                painter = painterResource(id = R.drawable.ic_mic), // Replace with actual mic icon
                                    imageVector = Icons.Default.AddCircle,
                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            // 🔊 Speaker Toggle Button
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(Color.Black, shape = CircleShape)
                                    .clickable { /* Toggle Speaker */ },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(

//                                painter = painterResource(id = R.drawable.ic_speaker), // Replace with actual speaker icon
                                    imageVector = Icons.Default.Delete,

                                    contentDescription = null,
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

                            // ❌ End Call Button
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .background(Color.Red, shape = CircleShape)
                                    .clickable {
                                        exoPlayer.release()
//                                        navController.navigateUp()
                                        navController.popBackStack()
//                                        navController.navigate(Routes.CallEndedScreen)
                                        val encodedPicPath = Uri.encode(profileImage)
                                        navController.navigate("CallEndedScreen/$encodedPicPath")

                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
//                                painter = painterResource(id = R.drawable.ic_call_end), // Replace with actual end call icon
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
//    }


}

//
//@OptIn(UnstableApi::class)
//@Composable
//fun FakeVideoCallScreen(
////    navController: NavHostController?,
////    callerName: String,
////    profileImageId: Int,
//) {
//    val context = LocalContext.current
//
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            val assetManager = context.assets
//            // Open the video file from assets
//            val fileDescriptor = assetManager.openFd("video/my_video.mp4") // Path relative to assets folder
//
//            // Create a MediaItem using the AssetFileDescriptor
//            val mediaItem = MediaItem.fromUri(Uri.parse("file://" + fileDescriptor.fileDescriptor.toString()))
//            setMediaItem(mediaItem)
//            repeatMode = Player.REPEAT_MODE_ONE // Loop video automatically
//            prepare()
//            playWhenReady = true
//        }
//    }
//
//    // ExoPlayer Setup for Video from Assets
////    val exoPlayer = remember {
////        ExoPlayer.Builder(context).build().apply {
////            val assetManager = context.assets
////            val fileDescriptor = assetManager.openFd("video/my_video.mp4") // Access video from assets
////            val mediaItem = MediaItem.fromUri(Uri.parse(fileDescriptor.uri.toString()))
////            setMediaItem(mediaItem)
////            repeatMode = Player.REPEAT_MODE_ONE // Loop video automatically
////            prepare()
////            playWhenReady = true
////        }
////    }
//
//    Scaffold {
//        Box(
//            modifier = Modifier.fillMaxSize().padding(it)
//        ) {
//            // Fake Video Call (Prerecorded Video from Assets)
//            AndroidView(
//                factory = { PlayerView(context).apply {
//                    player = exoPlayer
//                    useController = false // Hide video controls
//                    layoutParams = FrameLayout.LayoutParams(
//                        ViewGroup.LayoutParams.MATCH_PARENT,
//                        ViewGroup.LayoutParams.MATCH_PARENT
//                    )
//                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
//                }},
//            )
//
//            // User's Small Camera Feed (Static Image from Assets)
//            Box(
//                modifier = Modifier
//                    .padding(top = 36.dp, end = 16.dp)
//                    .align(Alignment.TopEnd)
//            ) {
//                Image(
//                    painter = rememberImagePainter("file:///android_asset/images/my_image.jpg"), // Load image from assets
//                    contentDescription = "User's Camera",
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier
//                        .size(100.dp)
//                        .clip(CircleShape)
//                        .border(4.dp, Color.White, CircleShape)
//                )
//            }
//
//            // Display Caller Name
//            Text(
//                text = "callerName",
//                color = Color.White,
//                fontSize = 18.sp,
//                modifier = Modifier
//                    .align(Alignment.TopCenter)
//                    .padding(top = 8.dp)
//            )
//
//            // Call Controls (Accept/End Call Buttons)
//            Box(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .align(Alignment.BottomCenter)
//                    .padding(vertical = 16.dp, horizontal = 16.dp)
//                    .background(
//                        color = Color.Black.copy(alpha = 0.5f),
//                        shape = RoundedCornerShape(10.dp)
//                    )
//                    .padding(8.dp)
//            ) {
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.SpaceEvenly
//                ) {
//                    // End Call Button
//                    Box(
//                        modifier = Modifier
//                            .size(50.dp)
//                            .background(Color.Red, shape = CircleShape)
//                            .clickable { exoPlayer.release() },
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Call,
//                            contentDescription = "End Call",
//                            tint = Color.White,
//                            modifier = Modifier.size(24.dp)
//                        )
//                    }
//
//                    // Accept Call Button (Simulate Answer)
//                    Box(
//                        modifier = Modifier
//                            .size(50.dp)
//                            .background(Color.Green, shape = CircleShape)
//                            .clickable { /* Simulate Answering the Call */ },
//                        contentAlignment = Alignment.Center
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Call,
//                            contentDescription = "Accept Call",
//                            tint = Color.White,
//                            modifier = Modifier.size(24.dp)
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
