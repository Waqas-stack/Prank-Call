package com.o9tech.prankcall.Screen.AudioCalling

import android.content.Context
import android.content.res.AssetFileDescriptor
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.o9tech.prankcall.R
import kotlinx.coroutines.delay
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

@Composable
fun AudioCallingScreen(
    navController: NavHostController?,
    audioPath: String,
    profileImage: Int,
    onCallAgain: () -> Unit,
    callerName: String,
) {
    val safeNavController = navController ?: rememberNavController()
    val context = LocalContext.current
    var currentPosition by remember { mutableStateOf(0) }
    val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager



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



    fun onCallEnd() {
        Toast.makeText(context, "Call Ended", Toast.LENGTH_SHORT).show()
        safeNavController.navigate("AudioCallEndedScreen/$callerName/$profileImage")


        Log.d("CallStatus", "Audio finished, call ended.")
    }

    val mediaPlayer = remember { MediaPlayer() }

    LaunchedEffect(audioPath) {
        try {
            val path = copyAssetToCache(context, audioPath)
            audioManager.mode = AudioManager.MODE_IN_COMMUNICATION
            audioManager.isSpeakerphoneOn = false
            if (path.exists()) {
                mediaPlayer.setDataSource(path.absolutePath)
                mediaPlayer.prepare()
                mediaPlayer.start()
            }


//            mediaPlayer.setOnCompletionListener {
//                onCallEnd()
//            }

            // Update position every second
            while (mediaPlayer.isPlaying) {
                currentPosition = mediaPlayer.currentPosition
                delay(1000)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }



    BackHandler {
        mediaPlayer.stop()
        safeNavController.popBackStack()
        mediaPlayer.release()
//        mediaPlayer = null
        safeNavController.navigate("AudioCallEndedScreen/$callerName/$profileImage")
    }

    DisposableEffect(Unit) {
        onDispose {
            try {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                }
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            } finally {
                mediaPlayer.release()
            }
        }
    }
    Scaffold(
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(it)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black)
                        .paint(
                            painter = rememberAsyncImagePainter(profileImage),
                            contentScale = ContentScale.Crop,
                            alpha = 0.22f
                        )
                ) {

                    Button(
                        modifier = Modifier
                            .height(65.dp)
                            .align(Alignment.TopEnd)
                            .padding(16.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Red,
                            contentColor = Color.White
                        ),
                        onClick = {
                            mediaPlayer.stop()
                            safeNavController.popBackStack()
                            mediaPlayer.release()
                            safeNavController.navigate("AudioCallEndedScreen/$callerName/$profileImage")
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_call_end_24),
                            contentDescription = "Call End",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 34.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .padding(top = 36.dp)
                                    .clickable {
                                        Log.d(" ", "image: $profileImage")
                                    }
                            ) {
                                Image(
                                    painter = painterResource(id = profileImage),
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .size(120.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                                Box(
                                    modifier = Modifier
                                        .size(130.dp)
                                        .clip(CircleShape)
                                        .background(Color.Transparent.copy(alpha = 0.5f))
                                        .align(Alignment.TopEnd)
                                )
                            }
                            Spacer(modifier = Modifier.height(16.dp))
                            Text(
                                text = callerName,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
//                                text = "00:12",
                                text = formatMillis(currentPosition),
                                fontSize = 16.sp,
                                color = Color.White.copy(alpha = 0.7f)
                            )
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
                                        color = Color.Black.copy(alpha = 0.2f),
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
                                            .clickable {  },
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
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}
@Preview(showBackground = true)
@Composable
fun PreviewIncommingCallScreen() {
    AudioCallingScreen(
        navController = rememberNavController(),
        audioPath = "audioPath",
        profileImage = R.drawable.img_home_messi,
        onCallAgain = {

        },
        callerName = "Rose",

        )
}



fun formatMillis(millis: Int): String {
    val seconds = (millis / 1000) % 60
    val minutes = (millis / 1000) / 60
    return String.format("%02d:%02d", minutes, seconds)
}
