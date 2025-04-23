package com.o9tech.prankcall.Screen.setFakeMessage

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.profilecircle
import com.o9tech.prankcall.ui.theme.rasish
import com.o9tech.prankcall.ui.theme.settingsclr
import com.o9tech.prankcall.ui.theme.textfrilssetvideocall
import com.o9tech.prankcall.viewModel.MainViewModel
import kotlinx.coroutines.launch




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverlappingBoxWithRoundedCorners(navController: NavHostController, ) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    var selectedImageUri2 by remember { mutableStateOf<Uri?>(null) }
    val safeNavController = navController ?: rememberNavController()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var charname by remember { mutableStateOf("") }
    var charname2 by remember { mutableStateOf("") }
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Friend", "Famous people")
    val pagerState = rememberPagerState {
        tabTitles.size
    }


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }
    val imagePickerLauncher2 = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri2 = uri
    }

    LaunchedEffect(pagerState.currentPage) {
        selectedTabIndex = pagerState.currentPage
    }

    Scaffold(
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Unspecified)
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(
                                color = rasish
                            ),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Row (
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ){
                            IconButton(onClick = {
                                safeNavController.popBackStack()
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.arrowleft),
                                    tint = Color.White,
                                    modifier = Modifier.size(20.dp),

                                    contentDescription = "Back")
                            }
                            Text(text = "Set fake message", color = Color.White, fontSize = 18.sp)
                        }
                        IconButton(onClick = {  }) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete_is),
                                tint = Color.White,
                                modifier = Modifier.size(20.dp),
                                contentDescription = "Back")
                        }

                    }
                    Box(
                        modifier = Modifier
                            .padding(top = 80.dp)
                            .clip(
                                RoundedCornerShape(
                                    topStart = 16.dp,
                                    topEnd = 16.dp
                                )
                            )
                            .background(Color.White)
                            .fillMaxWidth()
                    ) {

                        TabRow(
                            selectedTabIndex = pagerState.currentPage,
                            containerColor = Color.White,
                            modifier = Modifier
                                .fillMaxWidth(),
                            indicator = { tabPositions ->
                                TabRowDefaults.Indicator(
                                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                                    color = Color.Red,
                                    height = Dp(2f)
                                )
                            },
                            divider = {},
                        ) {

                            tabTitles.forEachIndexed { index, title ->
                                Tab(
                                    selected = pagerState.currentPage == index,
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    },
                                    text = {
                                        Text(
                                            text = title,
                                            fontSize = 16.sp, fontWeight = FontWeight.W400,
                                            color = Color.Red
                                        )
                                    }

                                )
                            }
                        }

                        HorizontalPager(
                            state = pagerState, modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            when (it) {
                                0 ->  Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(40.dp))
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(100.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(80.dp)
                                                .background(
                                                    color = Color.LightGray,
                                                    shape = CircleShape
                                                )
                                        )

                                        if (selectedImageUri != null) {
                                            Image(
                                                painter = rememberAsyncImagePainter(selectedImageUri),
                                                contentDescription = "Profile Picture",
                                                modifier = Modifier
                                                    .size(100.dp)
                                                    .clip(CircleShape),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                        else {
                                            Image(
                                                imageVector = Icons.Default.Person,
                                                contentDescription = "Profile Picture",
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .clip(CircleShape),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                        IconButton(
                                            onClick = {
                                                imagePickerLauncher.launch("image/*")
                                            },
                                            modifier = Modifier
                                                .align(Alignment.BottomEnd)
                                                .offset(
                                                    x = (1).dp,
                                                    y = (-15).dp
                                                )
                                                .background(
                                                    color = profilecircle,
                                                    shape = CircleShape
                                                )
                                                .padding(8.dp)
                                                .size(12.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Upload Icon",
                                                tint = settingsclr,

                                                )
                                        }
                                    }
                                    Row {
                                        Text(
                                            "Change Avatar",
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.padding(start = 6.dp),
                                            style = MaterialTheme.typography.titleMedium,
                                            color = Orange40
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Icon(
                                            imageVector = Icons.Default.Notifications,
                                            tint = Orange40,
                                            contentDescription = "Character Name"
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    TextField(
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Person,
                                                tint = Orange40,
                                                contentDescription = "Character Name"
                                            )
                                        },
                                        singleLine = true,
                                        maxLines = 1,
                                        shape = RoundedCornerShape(16.dp),
                                        colors = TextFieldDefaults.colors(
                                            focusedContainerColor = textfrilssetvideocall,
                                            unfocusedContainerColor = textfrilssetvideocall,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent
                                        ),
                                        value = charname,
                                        onValueChange = {
                                            charname=it
                                        },
                                        placeholder = { Text("Character Name") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(shape = RoundedCornerShape(10.dp))
                                            .height(60.dp)
                                            .background(textfrilssetvideocall),

                                        ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clickable {
                                                }
                                                .padding(horizontal = 10.dp, vertical = 5.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Notifications,
                                                    tint = Orange40,
                                                    contentDescription = "Character Name"
                                                )
                                                Spacer(modifier = Modifier.padding(10.dp))
                                                Column {
                                                    Text(
                                                        text = "Set Time",
                                                        fontWeight = FontWeight.W600,
                                                        fontSize = 16.sp
                                                    )
                                                    Text(text = "Now",fontSize = 12.sp,color = Color.DarkGray)
                                                }
                                            }
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {

                                                Icon(
                                                    imageVector = Icons.Default.KeyboardArrowRight,
                                                    tint = Color.DarkGray,
                                                    contentDescription = "Character Name"
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Spacer(modifier = Modifier.weight(1f))
                                    TextButton(
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = {
                                            if (charname.isNotEmpty() && selectedImageUri != null) {
                                                coroutineScope.launch {
                                            navController?.popBackStack()
                                        }
                                            }
                                        },
                                        border = BorderStroke(1.dp, Color.Red),
                                    ) {
                                        Text(text = "Save",color = Color.Red)

                                    }
                                }

                                1 ->  Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(16.dp),
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Spacer(modifier = Modifier.height(40.dp))

                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(100.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(80.dp)
                                                .background(
                                                    color = Color.LightGray,
                                                    shape = CircleShape
                                                )
                                        )

                                        if (selectedImageUri2 != null) {
                                            Image(
                                                painter = rememberAsyncImagePainter(selectedImageUri2),
                                                contentDescription = "Profile Picture",
                                                modifier = Modifier
                                                    .size(100.dp)
                                                    .clip(CircleShape),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                        else {
                                            Image(
                                                imageVector = Icons.Default.Person,
                                                contentDescription = "Profile Picture",
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .clip(CircleShape),
                                                contentScale = ContentScale.Crop
                                            )
                                        }

                                        IconButton(
                                            onClick = {
                                                imagePickerLauncher2.launch("image/*")
                                            },
                                            modifier = Modifier
                                                .align(Alignment.BottomEnd)
                                                .offset(
                                                    x = (1).dp,
                                                    y = (-15).dp
                                                )
                                                .background(
                                                    color = profilecircle,
                                                    shape = CircleShape
                                                )
                                                .padding(8.dp)
                                                .size(12.dp)
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Add,
                                                contentDescription = "Upload Icon",
                                                tint = settingsclr,

                                                )
                                        }
                                    }
                                    Row {
                                        Text(
                                            "Change Avatar",
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            modifier = Modifier.padding(start = 6.dp),
                                            style = MaterialTheme.typography.titleMedium,
                                            color = Orange40
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Icon(
                                            imageVector = Icons.Default.Notifications,
                                            tint = Orange40,
                                            contentDescription = "Character Name")
                                    }
                                    Spacer(modifier = Modifier.height(10.dp))
                                    TextField(
                                        leadingIcon = {
                                            Icon(
                                                imageVector = Icons.Default.Person,
                                                tint = Orange40,
                                                contentDescription = "Character Name"
                                            )
                                        },
                                        singleLine = true,
                                        maxLines = 1,
                                        shape = RoundedCornerShape(16.dp),
                                        colors = TextFieldDefaults.colors(
                                            focusedContainerColor = textfrilssetvideocall,
                                            unfocusedContainerColor = textfrilssetvideocall,
                                            focusedIndicatorColor = Color.Transparent,
                                            unfocusedIndicatorColor = Color.Transparent
                                        ),
                                        value = charname2,
                                        onValueChange = {
                                            charname2=it
                                        },
                                        placeholder = { Text("Character Name") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(shape = RoundedCornerShape(10.dp))
                                            .height(60.dp)
                                            .background(textfrilssetvideocall),

                                        ) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clickable {
                                                }
                                                .padding(horizontal = 10.dp, vertical = 5.dp),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.Notifications,
                                                    tint = Orange40,
                                                    contentDescription = "Character Name"
                                                )
                                                Spacer(modifier = Modifier.padding(10.dp))
                                                Column {
                                                    Text(
                                                        text = "Set Time",
                                                        fontWeight = FontWeight.W600,
                                                        fontSize = 16.sp
                                                    )
                                                    Text(text = "Now",fontSize = 12.sp,color = Color.DarkGray)
                                                }
                                            }
                                            Row(
                                                verticalAlignment = Alignment.CenterVertically,
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.KeyboardArrowRight,
                                                    tint = Color.DarkGray,
                                                    contentDescription = "Character Name"
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(15.dp))
                                    Spacer(modifier = Modifier.weight(1f))
                                    TextButton(
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = {
                                            if (charname2.isNotEmpty() && selectedImageUri2 != null) {
                                            }
                                        },
                                        border = BorderStroke(1.dp, Color.Red),
                                    ) {
                                        Text(text = "Save",color = Color.Red)

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
fun PreviewFakeMessageScreen() {
    OverlappingBoxWithRoundedCorners(
        navController = rememberNavController(),
    )
}




