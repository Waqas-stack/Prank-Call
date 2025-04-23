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
    val userDetails = mainViewModel.userDetailsList.collectAsState().value
    val context = LocalContext.current

//    val fakeVideo = listOf(
//        FakeVideoMessage("Rose", "drawable://img_home_iu", "/data/user/0/com.o9tech.prankcall/cache/call1.mp4" ,true),
//        FakeVideoMessage("Ronaldo", "drawable://img_get_started_ronadol","/data/user/0/com.o9tech.prankcall/cache/call2.mp4", true),
//        FakeVideoMessage("Messi", "drawable://img_home_messi", "/data/user/0/com.o9tech.prankcall/cache/prank.mp4",true),
//        FakeVideoMessage("Lisa", "drawable://img_get_started_jimin","/data/user/0/com.o9tech.prankcall/cache/call1.mp4" ,true),
//    )


    val fakeVideo = listOf(
        FakeVideoMessage("Rose", "drawable://img_home_iu", "call1.mp4" ,true),
        FakeVideoMessage("Ronaldo", "drawable://img_get_started_ronadol","call2.mp4", true),
        FakeVideoMessage("Messi", "drawable://img_home_messi", "prank.mp4",true),
        FakeVideoMessage("Lisa", "drawable://img_get_started_jimin","call1.mp4" ,true),
    )




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
                    columns = GridCells.Fixed(3),
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
                                    .size(100.dp)
                                    .clip(CircleShape)
                                    .clickable {
                                        when (index) {
                                            0 -> {
                                                safeNavController.navigate(Routes.AddCharacter)
                                            }

                                            else -> {
                                                    Log.d("pathing", "FakeVideoScreen: ${item.path}")
                                                Log.d("pictue", "FakeVideoScreen: ${item.pic}")
                                                safeNavController.navigate(
                                                    "SetVideoCallScreen/${item.name}/${Uri.encode(item.pic)}/${Uri.encode(item.path)}"
                                                )
                                            }
                                        }
                                    }
                                    .background(if (index == 0) settingsclr else Color.Unspecified)
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
                                                .size(100.dp)
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

