package com.o9tech.prankcall.Screen.IncommingAudioCallScreen

import android.net.Uri
import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.o9tech.prankcall.AppNavigation.Routes
import com.o9tech.prankcall.R

@Composable
fun IncommingAudioCall(
    navController: NavHostController?,
    audioPath: String,
    profileImage: Int,
    onCallAgain: () -> Unit,
    callerName: String,
) {
    val safeNavController = navController ?: rememberNavController()
    Scaffold (
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(vertical = 34.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier.clickable{
                                    Log.d("ImageCheck", "image: $profileImage")
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
                                text = "Incoming call..",
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
                                text = "Decline",
                                backgroundColor = Color.Red,
                                onClick = {
//                                    safeNavController.popBackStack()
                                    navController?.navigate(Routes.FakeAudioScreen) {
                                        popUpTo(navController.graph.startDestinationId) {
                                            inclusive = false // Don't remove root/start screen
                                        }
                                        launchSingleTop = true // Avoid multiple instances
                                    }
                                }

                            )

                            CircularButton(
                                icon = R.drawable.call,
                                text = "Accept",
                                backgroundColor = Color(0xFF4CAF50),
                                onClick = {
                                    val encodedPath = Uri.encode(audioPath)

                                    safeNavController.navigate("AudioCallingScreen/$callerName/$profileImage/$encodedPath")


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
fun RatingStars() {
    Row(horizontalArrangement = Arrangement.Center) {
        repeat(5) {
            Icon(
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
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
    IncommingAudioCall(
        navController = rememberNavController(),
        audioPath = "audioPath",
        profileImage = R.drawable.img_home_messi,
        onCallAgain = {
        },
        callerName = "Rose",
    )
}