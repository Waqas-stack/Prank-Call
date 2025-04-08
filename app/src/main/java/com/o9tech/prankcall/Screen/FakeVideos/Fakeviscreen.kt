package com.o9tech.prankcall.Screen.FakeVideos

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.o9tech.prankcall.AppNavigation.Routes
import com.o9tech.prankcall.DataModel.FakeMessage
import com.o9tech.prankcall.DataModel.FakeVideoMessage
import com.o9tech.prankcall.DataModel.LanguageItem
import com.o9tech.prankcall.R
import com.o9tech.prankcall.roomdb.databaseModel.VideoEntity
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.PurpleGrey80
import com.o9tech.prankcall.ui.theme.settingsclr
import com.o9tech.prankcall.utils.AppConstant.getVideoPathFromAssets

import com.o9tech.prankcall.viewModel.MainViewModel
import kotlinx.coroutines.launch
import java.io.File

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FakeVideoScreen(navController: NavHostController?, mainViewModel: MainViewModel) {
    val safeNavController = navController ?: rememberNavController()


    val fakeMessagesvideo = mainViewModel.allUsers.collectAsState().value
    val userDetails = mainViewModel.userDetailsList.collectAsState().value

//    val fakeMessage = listOf(
//
//        FakeMessage("Trivas", R.drawable.fake1),
//        FakeMessage("Smith", R.drawable.fake2),
//        FakeMessage("jhon", R.drawable.fake3),
//        FakeMessage("ayan", R.drawable.fake4),
//        FakeMessage("elisha", R.drawable.fake5),
//        FakeMessage("Nawaz", R.drawable.fake6),
//        FakeMessage("deph", R.drawable.fake7),
//        FakeMessage("Elsvish", R.drawable.fake8),
//        FakeMessage("United States", R.drawable.usa),
//        FakeMessage("Canada", R.drawable.canada),
//        FakeMessage("Turkey", R.drawable.turkey),
//        FakeMessage("UAE", R.drawable.dubai),
//        FakeMessage("Trivas", R.drawable.fake1),
//        FakeMessage("ayan", R.drawable.fake4),
//        FakeMessage("Elsvish", R.drawable.fake8),
//        FakeMessage("Turkey", R.drawable.turkey),
//    )
//    val fakeMessage = listOf(
//        LanguageItem("Rose", R.drawable.uk),
//        LanguageItem("Ronaldo", R.drawable.img_get_started_ronadol),
//        LanguageItem("Rose", R.drawable.img_home_iu),
//        LanguageItem("messi", R.drawable.img_home_messi),
//        LanguageItem("Lisa", R.drawable.img_get_started_jimin),
//
//    )


//    val fakeVideo = listOf(
//        LanguageItem("Rose", R.drawable.uk),
//        LanguageItem("Ronaldo", R.drawable.img_get_started_ronadol),
//        LanguageItem("Rose", R.drawable.img_home_iu),
//        LanguageItem("messi", R.drawable.img_home_messi),
//        LanguageItem("Lisa", R.drawable.img_get_started_jimin),
//
//        )



    val context = LocalContext.current

    val fakeVideo = listOf(
        FakeVideoMessage("Rose", "drawable://img_home_iu", "/data/user/0/com.o9tech.prankcall/cache/call1.mp4" ,true),
        FakeVideoMessage("Ronaldo", "drawable://img_get_started_ronadol","/data/user/0/com.o9tech.prankcall/cache/call2.mp4", true),
        FakeVideoMessage("Messi", "drawable://img_home_messi", "/data/user/0/com.o9tech.prankcall/cache/prank.mp4",true),
        FakeVideoMessage("Lisa", "drawable://img_get_started_jimin","/data/user/0/com.o9tech.prankcall/cache/call1.mp4" ,true),
    )




    // Combining the database data with the predefined list
//    val combinedFakeMessages = fakeMessagesvideo.map {
//        Log.d("Idssss", "Imgessss: ${it.imageUri}")
//        FakeVideoMessage(it.name, it.imageUri, false)
//    } + fakeVideo


    val combinedFakeMessages = userDetails.map {
        Log.d("Idssss", "Imgessss: ${it.videoPath}")
        FakeVideoMessage(it.name, it.imageName,it.videoPath, false)
    } + fakeVideo





    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Fake Video Call",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 6.dp),
//                        style = MaterialTheme.typography.titleMedium,
                        color = Orange40,
                        fontWeight = FontWeight.Bold

                    )
                },
                actions = {
                    IconButton(onClick = {
                        safeNavController.navigate(Routes.Search)
                    }) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "search",
                            modifier = Modifier.size(34.dp)
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { safeNavController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowleft),
                            contentDescription = "back",
                            tint = PurpleGrey80,
                            modifier = Modifier
                                .size(34.dp)
                                .padding(start = 6.dp)
                        )
//                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },

        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(3), // 3 columns
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                        .padding(5.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(0.dp)
                ) {
                    itemsIndexed(combinedFakeMessages) { index, item ->
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(100.dp) // Circle size
                                    .clip(CircleShape)
                                    .clickable {
                                        when (index) {
                                            0 -> {
                                                safeNavController.navigate(Routes.AddCharacter)
//                                                safeNavController.navigate(Routes.OverlappingBoxWithRoundedCorners)

                                            }

                                            else -> {
//                                                safeNavController.navigate(Routes.SetVideoCall)
//                                                safeNavController.navigate("SetVideoCallScreen/${item.name}/${item.pic}")
                                                    Log.d("pathing", "FakeVideoScreen: ${item.path}")
                                                Log.d("pictue", "FakeVideoScreen: ${item.pic}")
//                                                safeNavController.navigate("SetVideoCallScreen/${item.name}/${Uri.encode(item.pic)}"
                                                safeNavController.navigate(
                                                    "SetVideoCallScreen/${item.name}/${Uri.encode(item.pic)}/${Uri.encode(item.path)}"
                                                )


                                            }
//                                        safeNavController.navigate(Routes.AddCharacter)

                                        }
                                    }
                                    .background(if (index == 0) settingsclr else Color.Unspecified) // Conditional background color
                            ) {


                                if (index == 0) {
                                    Image(
                                        painterResource(id = R.drawable.plus),
                                        colorFilter = if (index == 0) ColorFilter.tint(Color.White) else null,
                                        contentDescription = null,
                                    )
                                } else {
                                    if (item.isDrawable) {
                                        val drawableId = context.resources.getIdentifier(
                                            item.pic.substringAfter("drawable://"),
                                            "drawable",
                                            context.packageName
                                        )
                                        Image(
                                            painter = painterResource(id = drawableId),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(100.dp) // Fits inside the circle
                                                .clip(CircleShape),
                                        )
                                    } else {
                                        if (!item.pic.isNullOrEmpty()) {


                                            Log.d("Picssssa", "Pictress: ${item.pic}")
                                            val painter = rememberAsyncImagePainter(File(item.pic))

                                            Image(
                                                painter = painter,
                                                contentDescription = "Profile Picture",
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(1000.dp)
                                                    .clip(CircleShape)
                                            )
                                        } else {
                                            Log.d("Picssssa", "No image found.")
                                            Image(
                                                imageVector = Icons.Default.Person,
                                                contentDescription = "Profile Picture",
                                                modifier = Modifier
                                                    .size(50.dp)
                                                    .clip(CircleShape),
                                                contentScale = ContentScale.Crop
                                            )
                                        }
                                    }

                                }


                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
//                                text = item.name,
                                text = if (index == 0) "Add New" else item.name,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

    )
}

