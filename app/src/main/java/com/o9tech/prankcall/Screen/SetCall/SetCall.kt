package com.o9tech.prankcall.Screen.SetCall

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.o9tech.prankcall.AppNavigation.Routes
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.blue
import com.o9tech.prankcall.ui.theme.grey
import com.o9tech.prankcall.ui.theme.setcallbg
import com.o9tech.prankcall.ui.theme.white


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetCallScreen(navController: NavHostController?, name: String, flag: Int) {

    val safeNavController = navController ?: rememberNavController()


    var isChecked by remember { mutableStateOf(false) }
    var isChecked2 by remember { mutableStateOf(false) }
    var isChecked3 by remember { mutableStateOf(false) }

    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri // Set selected image URI
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Set Call",
                        maxLines = 1,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 6.dp),
//                        style = MaterialTheme.typography.titleMedium,
                        color = blue
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        safeNavController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = com.o9tech.prankcall.R.drawable.arrowleft),
                            contentDescription = "back",
                            tint = grey,

                            modifier = Modifier.size(34.dp).padding(start = 10.dp)
                        )
                    }
                },
//                actions = {
//                    IconButton(onClick = {
//                        safeNavController.navigate(Routes.Search)
//                    }) {
//                        Icon(imageVector = Icons.Default.Search,
//                            contentDescription = "search",
//                            modifier = Modifier.size(34.dp)
//                        )
//                    }
//                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize().background(color = Color.White)
                    .padding(it)
            ) {
                Column(
                    Modifier
                        .fillMaxSize().background(color = Color.White)
                        .padding(16.dp),
//                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(120.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(color = Color.LightGray, shape = CircleShape)
                        )
                        if (selectedImageUri != null) {
                            Image(
                                painter = rememberAsyncImagePainter(selectedImageUri),
                                contentDescription = "Profile Picture",
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        } else {
                            Image(
//                                    imageVector = Icons.Default.Person,
                                painter = painterResource(id = flag), // Replace with your image
                                contentDescription = "Profile Picture",
                                modifier = Modifier
                                    .size(120.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                        }
//                            IconButton(
//                                onClick = {
//                                    imagePickerLauncher.launch("image/*")
//                                },
//                                modifier = Modifier
//                                    .align(Alignment.BottomEnd)
//                                    .offset(
////                                       x = 1.dp,
//                                        x = (-5).dp,
//                                        y = (-15).dp
//                                    )
//                                    .background(
////                                       color = Color.Blue,
//                                        color = profilecircle,
//                                        shape = CircleShape
//                                    ).padding(8.dp)
//                                    .size(17.dp)
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Add,
//                                    contentDescription = "Upload Icon",
//                                    tint = settingsclr,
//
//                                    )
//                            }


//                       ProfileImageUploader(onAddImageClick = { /* Handle add image click */ })
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
//                        text = "C.Ronaldo",
                        text = name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        modifier = Modifier
                            .width(320.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(setcallbg)
                            .padding(10.dp),
//                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = com.o9tech.prankcall.R.drawable.volume_down),
//                                    imageVector = Icons.Default.PlayArrow,
                                    contentDescription = "back",
                                    tint = blue,
                                    modifier = Modifier.size(20.dp)
                                )   

                                Text(
                                    text = "Sound",
                                    fontSize = 15.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                            Switch(
                                checked = isChecked,
                                onCheckedChange = {
                                    isChecked = it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = white,
                                    checkedTrackColor = blue,
                                    uncheckedThumbColor = white,
                                    uncheckedTrackColor = Color.LightGray,
                                    uncheckedBorderColor = Color.Transparent,
                                    checkedBorderColor = Color.Transparent
                                ),
                                modifier = Modifier.padding(end = 20.dp).size(20.dp,15.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .width(320.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(setcallbg)
                            .padding(10.dp),
//                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,

                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = com.o9tech.prankcall.R.drawable.vibration),
//                                    imageVector = Icons.Default.Person,
                                    contentDescription = "back",
                                    tint = blue,
                                    modifier = Modifier.size(20.dp)
                                )

                                Text(
                                    text = "Vibration",
                                    fontSize = 15.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                            Switch(
                                checked = isChecked2,
                                onCheckedChange = {
                                    isChecked2 = it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = white,
                                    checkedTrackColor = blue,
                                    uncheckedThumbColor = white,
                                    uncheckedTrackColor = Color.LightGray,
                                    uncheckedBorderColor = Color.Transparent,
                                    checkedBorderColor = Color.Transparent
                                ),
                                modifier = Modifier.padding(end = 20.dp).size(20.dp,15.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .width(320.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(setcallbg)
                            .padding(10.dp),
//                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,

                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = com.o9tech.prankcall.R.drawable.flash_on),
//                                    imageVector = Icons.Default.Person,
                                    contentDescription = "back",
                                    tint =blue,
                                    modifier = Modifier.size(20.dp)
                                )

                                Text(
                                    text = "Flash",
                                    fontSize = 15.sp,
                                    color = Color.Black,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }
                            Switch(
                                checked = isChecked3,
                                onCheckedChange = {
                                    isChecked3 = it
                                },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = white,
                                    checkedTrackColor = blue,
                                    uncheckedThumbColor = white,
                                    uncheckedTrackColor = Color.LightGray,
                                    uncheckedBorderColor = Color.Transparent,
                                    checkedBorderColor = Color.Transparent
                                ),
                                modifier = Modifier.padding(end = 20.dp).size(20.dp,15.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        modifier = Modifier.width(220.dp),
                        onClick = {
                            safeNavController.navigate(Routes.IncommingCallScreen)

                        },
//                        enabled = TODO(),
//                        shape = TODO(),
//                        colors = TODO(),
//                        elevation = TODO(),
                        border = BorderStroke(1.dp,blue),
//                        contentPadding = TODO(),
//                        interactionSource = TODO()
                    ) {
                        Row {
                            Icon(
//                               imageVector = Icons.Default.KeyboardArrowRight,
                                painter = painterResource(id = com.o9tech.prankcall.R.drawable.call),
                                tint =blue,
                                modifier = Modifier.size(20.dp),
                                contentDescription = "Character Name"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Call", color = blue)
                        }

                    }

                }
            }
        }
    )
}