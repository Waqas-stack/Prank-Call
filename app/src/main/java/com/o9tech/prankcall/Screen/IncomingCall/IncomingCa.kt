package com.o9tech.prankcall.Screen.IncomingCall


import android.net.Uri
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.o9tech.prankcall.R
import coil.compose.rememberAsyncImagePainter


@Composable
fun IncommingCallScreen(
    navController: NavHostController?,
    profileImage: String,
    onCallAgain: () -> Unit,
    callerName: String,
    videoPath: String,
) {
    val safeNavController = navController ?: rememberNavController()

    val drawableId = LocalContext.current.resources.getIdentifier(
        profileImage.substringAfter("drawable://"),
        "drawable",
        LocalContext.current.packageName
    )

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
                            if (drawableId != 0)
                                painterResource(id = drawableId) else rememberAsyncImagePainter(
                                profileImage
                            ),
                            contentScale = ContentScale.Crop,
                            alpha = 0.22f
                        )
                ) {

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
                                modifier = Modifier.clickable {
                                    Log.d("ImageCheck", "image: $profileImage")
                                }
                            ) {
                                if (drawableId != 0) {
                                    Image(
                                        painter = painterResource(id = drawableId),
                                        contentDescription = "Profile Picture",
                                        modifier = Modifier
                                            .size(130.dp)
                                            .clip(CircleShape)
                                            .border(4.dp, Color.White, CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                } else {
                                    AsyncImage(
                                        model = profileImage,
                                        contentDescription = "Profile Picture",
                                        modifier = Modifier
                                            .size(130.dp)
                                            .clip(CircleShape)
                                            .border(4.dp, Color.White, CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                }

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
//                                text = "Incoming call..",
                                text = stringResource(R.string.incoming_),
                                fontSize = 16.sp,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            CircularButton(
                                icon = R.drawable.baseline_call_end_24,
//                                text = "Decline",
                                text = stringResource(R.string.Decline),
                                backgroundColor = Color.Red,
                                onClick = {
                                    safeNavController.popBackStack()
                                }

                            )
                            CircularButton(
                                icon = R.drawable.call,
//                                text = "Accept",
                                text = stringResource(R.string.Accept),
                                backgroundColor = Color(0xFF4CAF50),
                                onClick = {
                                    Log.d("callpathh", "IncommingCallScreen: ${videoPath}")
                                    val encodedPath = Uri.encode(videoPath)
                                    val encodedPicPath = Uri.encode(profileImage)
                                    safeNavController.popBackStack()
                                    safeNavController.navigate("FakeVideoCall/$encodedPath/$encodedPicPath")


                                }
                            )
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun CircularButton(icon: Int, text: String, backgroundColor: Color, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(65.dp)
                .clip(CircleShape)
                .background(backgroundColor)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewIncommingCallScreen() {
    IncommingCallScreen(
        navController = rememberNavController(),
        profileImage = "drawable://fake1",
        onCallAgain = {
        },
        callerName = "Rose",
        videoPath = "https://example.com/video.mp4"
    )
}