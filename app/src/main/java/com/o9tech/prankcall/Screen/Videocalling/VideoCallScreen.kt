package com.o9tech.prankcall.Screen.Videocalling



import android.net.Uri
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import com.o9tech.prankcall.Screen.Apputils.CameraPreviewBox
import com.o9tech.prankcall.R


@OptIn(UnstableApi::class)
@Preview(showBackground = true)
@Composable
fun FakeVideoCallScreen() {
    val context = LocalContext.current


    // ExoPlayer Setup
//    val exoPlayer = remember {
//        ExoPlayer.Builder(context).build().apply {
//            val videoUri = Uri.parse("android.resource://${context.packageName}/${R.raw.prank}") // Add a sample video in raw folder
//            setMediaItem(MediaItem.fromUri(videoUri))
//            prepare()
//            playWhenReady = true
//        }
//    }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val videoUri = Uri.parse("android.resource://${context.packageName}/${R.raw.prank}")
            setMediaItem(MediaItem.fromUri(videoUri))
            repeatMode = Player.REPEAT_MODE_ONE // 🔥 Loop Video Automatically
            prepare()
            playWhenReady = true
        }
    }

    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            // Fake Video Call (Prerecorded Video)
//        AndroidView(
//            factory = { PlayerView(context).apply { player = exoPlayer } },
//            modifier = Modifier.fillMaxSize()
//        )
            AndroidView(
                factory = { PlayerView(context).apply {
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
                                .clickable { /* End Call */ },
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


}
