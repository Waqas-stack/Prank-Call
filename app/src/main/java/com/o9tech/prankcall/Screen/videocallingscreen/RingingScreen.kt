package com.o9tech.prankcall.Screen.videocallingscreen

import android.net.Uri
import androidx.compose.runtime.Composable

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.*
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.o9tech.prankcall.AppNavigation.Routes
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import com.o9tech.prankcall.R

@Composable
fun VideoCallingScreen(
    navController: NavHostController?,
    callerImage: String,
    callerName: String,
    onToggleVideo: () -> Unit,
    onToggleMic: () -> Unit,
    onToggleSpeaker: () -> Unit,
    onEndCall: () -> Unit,
) {
    val safeNavController = navController ?: rememberNavController()

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    var preview by remember { mutableStateOf<Preview?>(null) }
    val cameraExecutor: ExecutorService = remember { Executors.newSingleThreadExecutor() }

    val drawableId = LocalContext.current.resources.getIdentifier(
        callerImage.substringAfter("drawable://"), // Extract drawable name
        "drawable",
        LocalContext.current.packageName
    )

    Scaffold(
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Box(modifier = Modifier.fillMaxSize()) {
                    // 🔹 Selfie Camera Feed as Background

                    AndroidView(
                        factory = { ctx ->
                            val previewView = PreviewView(ctx).apply {
                                layoutParams = FrameLayout.LayoutParams(
                                    ViewGroup.LayoutParams.MATCH_PARENT,
                                    ViewGroup.LayoutParams.MATCH_PARENT
                                )
                                scaleType = PreviewView.ScaleType.FILL_CENTER
                            }

                            val cameraProviderFuture = ProcessCameraProvider.getInstance(ctx)
                            cameraProviderFuture.addListener({
                                val cameraProvider: ProcessCameraProvider =
                                    cameraProviderFuture.get()
                                val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                                preview = Preview.Builder().build().also {
                                    it.setSurfaceProvider(previewView.surfaceProvider)
                                }

                                try {
                                    cameraProvider.unbindAll()
                                    cameraProvider.bindToLifecycle(
                                        lifecycleOwner, cameraSelector, preview
                                    )
                                } catch (exc: Exception) {
                                    exc.printStackTrace()
                                }
                            }, ContextCompat.getMainExecutor(ctx))

                            previewView
                        },
                        modifier = Modifier.fillMaxSize()
                    )


                    // 🔹 Caller Profile Image
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = 50.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
//                        Image(
//                            painter = rememberAsyncImagePainter(callerImage),
//                            contentDescription = "Caller Profile",
//                            modifier = Modifier
//                                .size(90.dp)
//                                .clip(CircleShape)
//                                .border(4.dp, Color.White, CircleShape)
//                        )
                        if (drawableId != 0){
                            Image(
//                                   painter = rememberAsyncImagePainter(profileImage),
                                painter = painterResource(id = drawableId),
                                contentDescription = "Profile Picture",
                                modifier = Modifier
                                    .size(90.dp)
                                    .clip(CircleShape)
                                    .border(4.dp, Color.White, CircleShape),
                                contentScale = ContentScale.Crop
                            )}else{
                        AsyncImage(
//                                    model = profileImage,
                            model = callerImage,
                            contentDescription = "Caller",
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .border(4.dp, Color.White, CircleShape),
                            contentScale = ContentScale.Crop
                        )}
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = callerName,
                            fontSize = 22.sp,
                            color = Color.White
                        )
                        Text(
                            text = "Calling...",
                            fontSize = 14.sp,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }






                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 14.dp, start = 16.dp, end = 16.dp)
                            .background(
                                color = Color.Black.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(24.dp)
                            )
                            .padding(14.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
//                            CircularButton(icon = R.drawable.videocall, onClick = {})
                            ToggleCircularButton(
                                iconOn = R.drawable.videocall,
                                iconOff = R.drawable.videocam_off,
                                onToggle = { isOn -> println("Toggled: $isOn") }
                            )
                            ToggleCircularButton(
                                iconOn = R.drawable.mic,
                                iconOff = R.drawable.mic_off,
                                onToggle = { isOn -> println("Toggled: $isOn") }
                            )
//                            CircularButton(icon = R.drawable.mic, onClick = onToggleMic)
//                            CircularButton(icon = R.drawable.volume_down, onClick = onToggleSpeaker)
                            ToggleCircularButton(
                                iconOn = R.drawable.volume_down,
                                iconOff = R.drawable.volume_off,
                                onToggle = { isOn -> println("Toggled: $isOn") }
                            )
                            CircularButton(
                                icon = R.drawable.call,
                                backgroundColor = Color.Red,
                                onClick = {
                                    safeNavController.popBackStack()
                                    val encodedPicPath = Uri.encode(callerImage)
                                    safeNavController.navigate("CallEndedScreen/$encodedPicPath")
//                                    safeNavController.popBackStack()
//                                    safeNavController.navigate(Routes.CallEndedScreen )
                                },
                            )
                        }
                    }
                }
            }
        }
    )
}

// ✅ Custom Circular Button
@Composable
fun CircularButton(icon: Int, onClick: () -> Unit, backgroundColor: Color = Color.Black) {

    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(26.dp)
        )
    }
}


@Composable
fun ToggleCircularButton(
    iconOn: Int,
    iconOff: Int,
    onToggle: (Boolean) -> Unit,
    backgroundColor: Color = Color.Black,
) {
    var isToggled by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(CircleShape)
            .background(backgroundColor)
            .clickable {
                isToggled = !isToggled
                onToggle(isToggled)
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = if (isToggled) iconOn else iconOff),
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.size(26.dp)
        )
    }
}
