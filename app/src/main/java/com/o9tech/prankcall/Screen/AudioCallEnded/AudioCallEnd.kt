package com.o9tech.prankcall.Screen.AudioCallEnded

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.o9tech.prankcall.R
import com.o9tech.prankcall.Screen.SettingsSc.CustomRateUsDialog





@Composable
fun AudioCallEndedScreen(
    navController: NavHostController?,
    callername: String,
    profileImage: Int,
    onReturn: () -> Unit,
    onCallAgain: () -> Unit
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
                    IconButton(
                        modifier = Modifier.align(Alignment.TopStart).padding(16.dp),
                        onClick = {
                            safeNavController.popBackStack()
                        }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowleft),
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            Box(
                                contentAlignment = Alignment.Center
                            ) {

                                Box(
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(CircleShape)
                                        .background(Color.Transparent.copy(alpha = 0.5f))
                                        .align(Alignment.TopEnd)
                                ){
                                    Image(
                                        painter = painterResource(id = profileImage),
                                        contentDescription = "Profile Picture",
                                        modifier = Modifier
                                            .size(120.dp)
                                            .clip(CircleShape),
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                            Text(
                                text = "Call ended",
                                fontSize = 22.sp,
                                fontWeight = FontWeight.W400,
                                color = Color.White
                            )
                            Text(
                                text = "00:14",
                                fontSize = 14.sp,
                                color = Color.White.copy(alpha = 0.7f)
                            )
                        }
                        Column (
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ){
                            Text(
                                text = "How was the quality of your call?",
                                fontSize = 14.sp,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                            RatingStarsWithDialog()
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            CircularButton(
                                icon = R.drawable.baseline_call_end_24,
                                text = "Return",
                                backgroundColor = Color.Red,
                                onClick = onReturn
                            )
                            CircularButtonWithWave(
                                icon = R.drawable.call,
                                text = "Call again",
                                backgroundColor = Color.Blue,
                                onClick = onCallAgain
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
                .size(60.dp)
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
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun CircularButtonWithWave(
    icon: Int,
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()
    val waveScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )
    val waveAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(contentAlignment = Alignment.Center) {
        Canvas(
            modifier = Modifier
                .size(80.dp)
                .graphicsLayer(scaleX = waveScale, scaleY = waveScale, alpha = waveAlpha)
        ) {
            drawCircle(color = Color.White.copy(alpha = 0.3f))
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(60.dp)
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
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun RatingStarsWithDialog() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedRating by remember { mutableStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(horizontalArrangement = Arrangement.Center) {
            repeat(5) { index ->
                Icon(
                    imageVector = if (index < selectedRating) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = null,
                    tint = if (index < selectedRating) Color(0xFFFFD700) else Color.Gray,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            selectedRating = index + 1
                            showDialog = true
                        }
                )
            }
        }
        CustomRateUsDialog(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            onSubmit = { rating ->
                selectedRating = rating
                showDialog = false
                println("User rated: $rating stars")
            }
        )
    }
}




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCallEndedScreen() {
    AudioCallEndedScreen(
        navController = rememberNavController(),
        profileImage = R.drawable.img_home_messi,
        onReturn = {},
        callername = "Messi",
        onCallAgain = {}
    )
}