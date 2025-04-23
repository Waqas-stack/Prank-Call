package com.o9tech.prankcall.Screen

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.o9tech.prankcall.AppNavigation.Routes
import com.o9tech.prankcall.DataModel.LanguageItem
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.Purple40
import com.o9tech.prankcall.ui.theme.blue
import com.o9tech.prankcall.ui.theme.settingsclr
import com.o9tech.prankcall.viewModel.MainViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.graphicsLayer
import com.o9tech.prankcall.ui.theme.divider
import com.o9tech.prankcall.ui.theme.grey
import com.o9tech.prankcall.ui.theme.profilecircle


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController?, mainViewModel: MainViewModel) {
    val safeNavController = navController ?: rememberNavController()
    val user =mainViewModel.allUsers.collectAsState()
    HomeScreenDesign(

        onSettingClick = {
            safeNavController.navigate(Routes.Setting)
        },
        onSearchClick = {
            safeNavController.navigate(Routes.Search)
        },
        onVideoClick = {
            safeNavController.navigate(Routes.FakeVideoScreen)
        },
        onMessageClick = {
            safeNavController.navigate(Routes.FakeMessage)
        },
        onAudioCallClick = {
            safeNavController.navigate(Routes.FakeAudioScreen)
        }
    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenDesign(
    onSettingClick: () -> Unit,
    onSearchClick: () -> Unit,
    onVideoClick: () -> Unit,
    onMessageClick: () -> Unit,
    onAudioCallClick: () -> Unit,

    ) {
    val gradientColors = listOf(Orange40, settingsclr, Purple40)
    val fakeVideoCallList = listOf(
        LanguageItem("Rose", R.drawable.img_get_started_jenny),
        LanguageItem("Lisa", R.drawable.img_get_started_jisoo),
        LanguageItem("Elsa", R.drawable.img_home_carrdi),
        LanguageItem("more", R.drawable.ic_more_fake_video_call),
    )

    val fakeMessagesList = listOf(
        LanguageItem("jisoo", R.drawable.img_get_started_jisoo),
        LanguageItem("Messi ", R.drawable.img_home_messi),
        LanguageItem("jennie", R.drawable.img_home_selena_gomez),
        LanguageItem("more", R.drawable.ic_more_fake_message),


        )
    val fakeAudioCallList = listOf(
        LanguageItem("iu", R.drawable.img_home_iu),
        LanguageItem("RM", R.drawable.img_get_started_lisa),
        LanguageItem("jungkook", R.drawable.img_get_started_jungkook),
        LanguageItem("more", R.drawable.ic_more_fake_call),

        )
    Scaffold(
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End

                ){
                    IconButton(onClick = {
                        onSearchClick()

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.diamond),
                            contentDescription = "Back",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(34.dp)
                        )
                    }
                    IconButton(onClick = {
                        onSettingClick()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_settings),
                            contentDescription = "Back",
                            tint =settingsclr,
                            modifier = Modifier.size(34.dp)
                        )
                    }
                }
                Image(
                    painter = painterResource(id = R.drawable.img_frank_app),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    )
                Text(text = "Fake Video Call & chat", fontSize = 15.sp, color = Orange40, fontWeight = FontWeight.W400)
            }
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.White)
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_fake_video_call),
                            contentDescription = "Back",
                            tint = settingsclr,
                            modifier = Modifier.size(34.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "FAKE VIDEO CALL",
                            fontSize = 14.sp,
                            color = settingsclr,
                            fontWeight = FontWeight.W600
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    LazyRow(
                        modifier = Modifier
                            .background(color = Color.White)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        items(fakeVideoCallList.size) { index ->
                            val language = fakeVideoCallList[index]
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .padding(8.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(CircleShape)
                                        .clickable {
                                            onVideoClick()
                                        }
                                        .background(profilecircle)
                                ) {
                                    Image(
                                        painter = painterResource(id = language.flag),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(70.dp)
                                            .clip(CircleShape)
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = language.name,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W600,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Divider(
                        color = divider,
                        thickness = 1.dp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.chat),
                            contentDescription = "Chat",
                            tint = Orange40,
                            modifier = Modifier.size(34.dp).graphicsLayer(scaleX = -1f)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "FAKE MESSAGE",
                            fontSize = 14.sp,
                            color = Orange40,
                            fontWeight = FontWeight.W600
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        items(fakeMessagesList.size) { index ->
                            val language = fakeMessagesList[index]
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(CircleShape)
                                        .clickable {
                                            onMessageClick()
                                        }
                                        .background(profilecircle)
                                ) {
                                    Image(
                                        painter = painterResource(id = language.flag),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(70.dp)
                                            .clip(CircleShape)
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = language.name,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W600,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Divider(
                        color = divider,
                        thickness = 1.dp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.phone),
                            contentDescription = "call",
                            tint = blue,
                            modifier = Modifier.size(34.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "FAKE CALL",
                            fontSize = 14.sp,
                            color = blue,
                            fontWeight = FontWeight.W600
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        items(fakeAudioCallList.size) { index ->
                            val language = fakeAudioCallList[index]
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(CircleShape)
                                        .clickable {
                                            onAudioCallClick()
                                        }
                                        .background(profilecircle)
                                ) {
                                    Image(
                                        painter = painterResource(id = language.flag),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(70.dp)
                                            .clip(CircleShape)
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = language.name,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.W600,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Divider(
                        color = divider,
                        thickness = 1.dp
                    )

                }
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun PreviewHomeScreen() {
    HomeScreenDesign(
        onSettingClick = {},
        onSearchClick = {},
        onVideoClick = {},
        onMessageClick = {},
        onAudioCallClick = {}
    )
}