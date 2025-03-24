package com.o9tech.prankcall.Screen.callended

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.o9tech.prankcall.R
import coil.compose.rememberAsyncImagePainter




@Composable
fun CallEndedScreen(
    navController: NavHostController?,
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
                               Image(
                                   painter = rememberAsyncImagePainter(profileImage),
                                   contentDescription = "Profile Picture",
                                   modifier = Modifier
                                       .size(90.dp)
                                       .clip(CircleShape)
                                       .border(4.dp, Color.White, CircleShape)
                               )
                               Box(
                                   modifier = Modifier
                                       .size(100.dp)
                                       .clip(CircleShape)
                                       .background(Color.Transparent.copy(alpha = 0.5f))
                                       .align(Alignment.TopEnd)
                               )
                           }
                           Text(
                               text = "Call ended",
                               fontSize = 22.sp,
                               fontWeight = FontWeight.Bold,
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
                               fontSize = 16.sp,
                               color = Color.White.copy(alpha = 0.8f)
                           )
                           RatingStars()
                       }
                       Row(
                           modifier = Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceEvenly
                       ) {
                           CircularButton(
                               icon = R.drawable.phone,
                               text = "Return",
                               backgroundColor = Color.Gray,
                               onClick = onReturn
                           )

                           CircularButton(
                               icon = R.drawable.videocall,
                               text = "Call again",
                               backgroundColor = Color(0xFFFF9800),
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
fun RatingStars() {
    Row(horizontalArrangement = Arrangement.Center) {
        repeat(5) {
            Icon(
//                painter = painterResource(id = R.drawable.ic_star), // Replace with star icon
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
                .size(80.dp)
                .clip(CircleShape)
                .background(backgroundColor)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(40.dp)
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




@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCallEndedScreen() {
    CallEndedScreen(
        navController = rememberNavController(),
        profileImage = R.drawable.fake1,
        onReturn = {}
    ) {}
}
