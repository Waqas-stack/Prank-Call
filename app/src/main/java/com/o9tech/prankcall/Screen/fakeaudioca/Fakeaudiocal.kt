package com.o9tech.prankcall.Screen.fakeaudioca

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.o9tech.prankcall.AppNavigation.Routes
import com.o9tech.prankcall.DataModel.AudiocallData
import com.o9tech.prankcall.DataModel.FakeMessage
import com.o9tech.prankcall.DataModel.LanguageItem
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.PurpleGrey80
import com.o9tech.prankcall.ui.theme.setcall

@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FakeAudioScreen(navController: NavHostController?) {
    val safeNavController = navController ?: rememberNavController()

    val fakeAudio = listOf(
        AudiocallData("Iu", R.drawable.img_home_iu, "audiocall1.mp3"),
        AudiocallData("messi", R.drawable.img_home_messi, "audiocall2.mp3"),
        AudiocallData("jimin", R.drawable.img_get_started_jimin, "audiocall3.mp3"),
        AudiocallData("CardiB", R.drawable.img_home_carrdi, "audiocall4.mp3"),
        AudiocallData("cha", R.drawable.img_get_started_cha_eunwoo, "audiocall5.mp3"),
    )




    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Fake Call",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 6.dp),
                        color = setcall
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
                    items(fakeAudio.size) { item ->
                        val item = fakeAudio[item]
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
                                        Log.d("Sendpath", "FakeAudioScreen: ${item.audioPath}")
                                        val encodedPath = Uri.encode(item.audioPath)
                                        safeNavController.navigate("SetCallScreen/${item.name}/${item.flag}/$encodedPath")


                                    }
                                    .background(Color.LightGray)
                            ) {
                                Image(
                                    painter = painterResource(id = item.flag),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(CircleShape)
                                )
                            }
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(
                                text = item.name,
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