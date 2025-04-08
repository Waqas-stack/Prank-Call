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

//
//@Preview(showBackground = true)
//@Composable
//fun setFakeMsgScreen(){
//    Scaffold (
//        content = {
//            Surface(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(it)
//                    .padding(16.dp)
//            ) {
//
//            }
//        }
//    )
//}


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverlappingBoxWithRoundedCorners(navController: NavHostController, mainViewModel: MainViewModel) {
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val safeNavController = navController ?: rememberNavController()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var charname by remember { mutableStateOf("") }
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Friend", "Famous people")
    val pagerState = rememberPagerState {
        tabTitles.size
    }


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri // Set selected image URI
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
//                                    Icons.Default.ArrowBack,
                                    contentDescription = "Back")
                            }
                            Text(text = "Set fake message", color = Color.White, fontSize = 18.sp)
                        }
                        IconButton(onClick = { /* Handle navigation icon click */ }) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete_is),
                                tint = Color.White,
                                modifier = Modifier.size(20.dp),
//                                    Icons.Default.ArrowBack,
                                contentDescription = "Back")
                        }

                    }
                    // TopAppBar at the top of the screen
//                    TopAppBar(
////                        modifier = Modifier.height(100.dp),
//                        title = { Text("Set fake message", color = Color.White, fontSize = 12.sp) },
//                        colors = TopAppBarDefaults.topAppBarColors(
//                            containerColor = rasish,
//                        ),
//                        navigationIcon = {
//                            // Add navigation icon if needed
//                            IconButton(onClick = { /* Handle navigation icon click */ }) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.arrowleft),
//                                    tint = Color.White,
//                                    modifier = Modifier.size(20.dp),
////                                    Icons.Default.ArrowBack,
//                                    contentDescription = "Back")
//                            }
//                        },
//                        actions = {
//                            IconButton(onClick = {}) {
//                                Icon(
//                                    painter = painterResource(id = R.drawable.delete_is),
//                                    tint = Color.White,
//                                    modifier = Modifier.size(24.dp),
//                                    contentDescription = "Delete"
//                                )
//                            }
//                        },
//                        modifier = Modifier.height(100.dp).align(Alignment.TopCenter)
//                    )

                    // Content that overlaps the TopAppBar
                    Box(
                        modifier = Modifier
                            .padding(top = 80.dp) // Adjust padding to position below the TopAppBar
                            .clip(
                                RoundedCornerShape(
                                    topStart = 16.dp,
                                    topEnd = 16.dp
                                )
                            )
                            .background(Color.White)
                            .fillMaxWidth()
                             // Adjust height as needed
                    ) {

                        TabRow(
//                            selectedTabIndex = selectedTabIndex,
                            selectedTabIndex = pagerState.currentPage,
//                            containerColor = tabbg,
                            containerColor = Color.White,
                            modifier = Modifier
                                .fillMaxWidth(),
                            indicator = { tabPositions ->
                                TabRowDefaults.Indicator(
                                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                                    color = Color.Red, // Change color as needed
                                    height = Dp(2f) // Adjust height as needed
                                )
                            },
//                            indicator = { tabPositions ->
//                                Box(
//                                    modifier = Modifier
//                                        .tabIndicatorOffset(tabPositions[pagerState.currentPage])
//                                        .fillMaxSize()
//                                        .clip(RoundedCornerShape(16.dp))
//                                        .then(Modifier.background(Color.White))
//                                        .zIndex(-1f)
//                                )
//                            },
                            divider = {},
//
                        ) {

                            tabTitles.forEachIndexed { index, title ->
                                Tab(
                                    selected = pagerState.currentPage == index,
//                                    selected = selectedTabIndex == index,
//                                    onClick = { selectedTabIndex = index },
                                    onClick = {
                                        coroutineScope.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    },
                                    text = {
                                        Text(
                                            text = title,
                                            fontSize = 16.sp, fontWeight = FontWeight.W400,
//                                            color = if (selectedTabIndex == index) Color.Black else Color.Gray // Selected text color
                                            color = Color.Red // Selected text color
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
//                               painter = painterResource(id = R.drawable.uk), // Replace with your image
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
//                                       x = 1.dp,
                                                    x = (1).dp,
                                                    y = (-15).dp
                                                )
                                                .background(
//                                       color = Color.Blue,
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
//                       ProfileImageUploader(onAddImageClick = { /* Handle add image click */ })
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
//                                colors = TextFieldDefaults.colors(
//                                    focusedContainerColor = grey,
//                                    unfocusedContainerColor = grey,
//                                    cursorColor = Color.Black,
//                                    focusedIndicatorColor = Color.Transparent,
//                                    unfocusedIndicatorColor = Color.Transparent,
////                            focusedTextColor = Color.White,
////                            unfocusedTextColor = Color.White,
//                                ),
                                        value = charname,
                                        onValueChange = {
                                            charname=it
                                        },
                                        placeholder = { Text("Character Name") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
//                            Text(
//                                "Video call ",
//                                maxLines = 1,
//                                overflow = TextOverflow.Ellipsis,
//                                modifier = Modifier.padding(start = 6.dp),
//                                style = MaterialTheme.typography.titleMedium,
//                                color = Orange40
//                            )
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
//                                            showDialog = true
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
//                                Icon(
//                                    imageVector = Icons.Default.Person,
//                                    tint = Orange40,
//                                    contentDescription = "Character Name"
//                                )
//                                Spacer(modifier = Modifier.padding(10.dp))
                                                Icon(
                                                    imageVector = Icons.Default.KeyboardArrowRight,
                                                    tint = Color.DarkGray,
                                                    contentDescription = "Character Name"
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(15.dp))
//                            Box(
//                                modifier = Modifier
//                                    .size(120.dp).clip(RoundedCornerShape(4.dp))
//                                    .dashedBorder(2.dp, settingsclr, 8.dp).clickable{
//
//                                    },
//                                contentAlignment = Alignment.Center
//                            ) {
//                                Column(
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    // 🔹 Icon
//                                    Icon(
////                                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your icon
//                                        imageVector = Icons.Default.Add,
//                                        contentDescription = "Icon",
//                                        tint = settingsclr,
//                                        modifier = Modifier.size(24.dp)
//                                    )
//
//                                    Spacer(modifier = Modifier.height(8.dp))
//
//                                    // 🔹 Text
//                                    Text(
//                                        text = "Upload Video",
//                                        fontSize = 12.sp,
//                                        color = Color.Black
//                                    )
//                                }
//                            }
                                    Spacer(modifier = Modifier.weight(1f))
                                    TextButton(
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = {
                                            if (charname.isNotEmpty() && selectedImageUri != null) {
                                                coroutineScope.launch {
//                                            mainViewModel.saveUser(charname, selectedImageUri.toString())
                                            navController?.popBackStack()
                                        }
                                            }
                                        },
//                        enabled = TODO(),
//                        shape = TODO(),
//                        colors = TODO(),
//                        elevation = TODO(),
                                        border = BorderStroke(1.dp, Color.Red),
//                        contentPadding = TODO(),
//                        interactionSource = TODO()
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
//                               painter = painterResource(id = R.drawable.uk), // Replace with your image
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
//                                       x = 1.dp,
                                                    x = (1).dp,
                                                    y = (-15).dp
                                                )
                                                .background(
//                                       color = Color.Blue,
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
//                       ProfileImageUploader(onAddImageClick = { /* Handle add image click */ })
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
//                                colors = TextFieldDefaults.colors(
//                                    focusedContainerColor = grey,
//                                    unfocusedContainerColor = grey,
//                                    cursorColor = Color.Black,
//                                    focusedIndicatorColor = Color.Transparent,
//                                    unfocusedIndicatorColor = Color.Transparent,
////                            focusedTextColor = Color.White,
////                            unfocusedTextColor = Color.White,
//                                ),
                                        value = charname,
                                        onValueChange = {
                                            charname=it
                                        },
                                        placeholder = { Text("Character Name") },
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
//                            Text(
//                                "Video call ",
//                                maxLines = 1,
//                                overflow = TextOverflow.Ellipsis,
//                                modifier = Modifier.padding(start = 6.dp),
//                                style = MaterialTheme.typography.titleMedium,
//                                color = Orange40
//                            )
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
//                                            showDialog = true
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
//                                Icon(
//                                    imageVector = Icons.Default.Person,
//                                    tint = Orange40,
//                                    contentDescription = "Character Name"
//                                )
//                                Spacer(modifier = Modifier.padding(10.dp))
                                                Icon(
                                                    imageVector = Icons.Default.KeyboardArrowRight,
                                                    tint = Color.DarkGray,
                                                    contentDescription = "Character Name"
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(15.dp))
//                            Box(
//                                modifier = Modifier
//                                    .size(120.dp).clip(RoundedCornerShape(4.dp))
//                                    .dashedBorder(2.dp, settingsclr, 8.dp).clickable{
//
//                                    },
//                                contentAlignment = Alignment.Center
//                            ) {
//                                Column(
//                                    horizontalAlignment = Alignment.CenterHorizontally
//                                ) {
//                                    // 🔹 Icon
//                                    Icon(
////                                painter = painterResource(id = R.drawable.ic_launcher_foreground), // Replace with your icon
//                                        imageVector = Icons.Default.Add,
//                                        contentDescription = "Icon",
//                                        tint = settingsclr,
//                                        modifier = Modifier.size(24.dp)
//                                    )
//
//                                    Spacer(modifier = Modifier.height(8.dp))
//
//                                    // 🔹 Text
//                                    Text(
//                                        text = "Upload Video",
//                                        fontSize = 12.sp,
//                                        color = Color.Black
//                                    )
//                                }
//                            }
                                    Spacer(modifier = Modifier.weight(1f))
                                    TextButton(
                                        modifier = Modifier.fillMaxWidth(),
                                        onClick = {
                                            if (charname.isNotEmpty() && selectedImageUri != null) {
//                                        scope.launch {
////                                            mainViewModel.saveUser(charname, selectedImageUri.toString())
////                                            navController?.popBackStack()
//                                        }
                                            }
                                        },
//                        enabled = TODO(),
//                        shape = TODO(),
//                        colors = TODO(),
//                        elevation = TODO(),
                                        border = BorderStroke(1.dp, Color.Red),
//                        contentPadding = TODO(),
//                        interactionSource = TODO()
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


//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Delete
//import androidx.compose.material.icons.filled.Edit
//import androidx.compose.material.icons.filled.Person
//
//import androidx.compose.material3.*
//
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//
//import androidx.compose.ui.unit.sp
//import com.o9tech.prankcall.R
//
//
//@Composable
//fun FakeMessageScreen() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color(0xFFFF4F4F))
//    ) {
//        TopBar()
//        Content()
//    }
//}
//
//@Composable
//fun TopBar() {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        IconButton(onClick = { /*TODO*/ }) {
//            Icon(
////                imageVector = Icons.Default.ArrowBack,
//                painterResource(id = R.drawable.arrowleft),
//                contentDescription = "Back"
//            )
//        }
//        Text(
//            text = "Set Fake Message",
//            fontSize = 20.sp,
//            color = Color.White,
//            fontWeight = FontWeight.Bold
//        )
//        Spacer(modifier = Modifier.weight(1f))
//        IconButton(onClick = { /*TODO*/ }) {
//            Icon(
//                imageVector = Icons.Default.Delete,
////                painterResource(id = R.drawable.ic_delete),
//                contentDescription = "Delete")
//        }
//    }
//}
//
//@Composable
//fun Content() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        TabRow()
//        Spacer(modifier = Modifier.height(16.dp))
//        AvatarSection()
//        Spacer(modifier = Modifier.height(16.dp))
//        InputFields()
//        Spacer(modifier = Modifier.weight(1f))
//        SendButton()
//    }
//}
//
//@Composable
//fun TabRow() {
//    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
//        Text("Friend", fontSize = 16.sp, color = Color.Black, fontWeight = FontWeight.Bold)
//        Text("Famous people", fontSize = 16.sp, color = Color.Gray)
//    }
//    Divider(color = Color.Red, thickness = 2.dp, modifier = Modifier.padding(horizontal = 64.dp))
//}
//
//@Composable
//fun AvatarSection() {
//    Box(contentAlignment = Alignment.BottomEnd) {
//        Box(
//            modifier = Modifier
//                .size(80.dp)
//                .clip(CircleShape)
//                .background(Color.Gray)
//        )
//        Icon(
//            painter = painterResource(id = R.drawable.volume_off),
////            iamgeVector = Icons.Default.Edit,
//            contentDescription = "Edit Avatar",
//            tint = Color.Red,
//            modifier = Modifier
//                .size(24.dp)
//                .align(Alignment.BottomEnd)
//        )
//    }
//    Text("Change Avatar", fontSize = 14.sp, color = Color.Red)
//}
//
//@Composable
//fun InputFields() {
//    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
//        InputField("hg")
//        Spacer(modifier = Modifier.height(8.dp))
//        InputField("Choose Theme", isHint = true)
//    }
//}
//
//@Composable
//fun InputField(text: String, isHint: Boolean = false) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .background(Color(0xFFF0F0F0), RoundedCornerShape(8.dp))
//            .padding(16.dp),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        Icon(
////            painterResource(id = R.drawable.ic_user),
//            imageVector = Icons.Default.Person,
//            contentDescription = "User", tint = Color.Red)
//        Spacer(modifier = Modifier.width(8.dp))
//        Text(text, fontSize = 16.sp, color = if (isHint) Color.Gray else Color.Black)
//    }
//}
//
//@Composable
//fun SendButton() {
//    Button(
//        onClick = { /*TODO*/ },
//        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
//        shape = RoundedCornerShape(50),
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp)
//    ) {
//        Text("Send", fontSize = 18.sp, color = Color.Red)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PreviewFakeMessageScreen() {
//    FakeMessageScreen()
//}

