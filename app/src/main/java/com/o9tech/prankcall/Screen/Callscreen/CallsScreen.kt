package com.o9tech.prankcall.Screen.Callscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.black
import com.o9tech.prankcall.ui.theme.white

//
//@Composable
//fun CallsSc(){
//    Scaffold (
//        content = {
//            Surface (
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(it)
//                    .padding(8.dp)
//            ){
//                Column {
//                    Text(text = "Calls")
//                }
//            }
//        }
//    )
//}


@Preview(showBackground = true)
@Composable
fun CallScreen(
    callerName: String = "John Doe",
    callerImage: Int = R.drawable.uk, // Replace with your drawable
    onAnswer: () -> Unit,
    onDecline: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(black),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = callerImage),
                contentDescription = "Caller Image",
                modifier = Modifier
                    .size(120.dp)
                    .clip(CircleShape)
                    .border(2.dp, white, CircleShape)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = callerName,
                color = white,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "Incoming Call...",
                color = Color.LightGray,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = onDecline) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Decline",
                        tint = Color.Red,
                        modifier = Modifier.size(60.dp)
                    )
//                    Icon(
//                        painter = painterResource(id = R.drawable.decline),
//                        contentDescription = "Decline",
////                        tint = Color.Red,
//                        modifier = Modifier.size(60.dp)
//                    )
                }

                IconButton(onClick = onAnswer) {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = "Answer",
                        tint = Color.Green,
                        modifier = Modifier.size(60.dp)
                    )
                }
            }
        }
    }
}
