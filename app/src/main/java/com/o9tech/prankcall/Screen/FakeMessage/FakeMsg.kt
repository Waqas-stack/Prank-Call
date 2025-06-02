package com.o9tech.prankcall.Screen.FakeMessage

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.android.gms.ads.AdSize
import com.o9tech.prankcall.Add.BannerAds.BannersAds
import com.o9tech.prankcall.AppNavigation.Routes
import com.o9tech.prankcall.DataModel.FakeMessage
import com.o9tech.prankcall.DataModel.FakeVideoMessage
import com.o9tech.prankcall.DataModel.LanguageItem
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.PurpleGrey80
import com.o9tech.prankcall.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FakeMessageScreen(navController: NavHostController?, mainViewModel: MainViewModel) {
    val safeNavController = navController ?: rememberNavController()
    val fakeMessages = mainViewModel.fakeMessage.collectAsState().value


    val predefinedItems = arrayListOf<FakeMessage>(
        FakeMessage(stringResource(R.string.add_new), "drawable://add_add",  true),
        FakeMessage("Jisoo", "drawable://img_get_started_jisoo",true),
        FakeMessage("Messi", "drawable://img_home_messi",true),
        FakeMessage("jennie", "drawable://img_home_selena_gomez",true),
    )


    val context= LocalContext.current

    predefinedItems.addAll(fakeMessages.map {
        FakeMessage(it.name, it.imageUri, false)
    })
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
//                        "Fake message",
                        text = stringResource(R.string.fake_message),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 6.dp),
                        color = Orange40,
                        fontWeight = FontWeight.Bold
                    )
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
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)
                            .padding(5.dp),
                        //                    verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        itemsIndexed(predefinedItems) { index, item ->
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
                                                    safeNavController.navigate(Routes.AddCharacterMsg)
                                                }

                                                else -> {
                                                    //                                                safeNavController.navigate(Routes.OverlappingBoxWithRoundedCorners)
                                                    safeNavController.navigate(
                                                        "OverlappingBoxWithRoundedCorners/${item.name}/${
                                                            Uri.encode(
                                                                item.pic
                                                            )
                                                        }"
                                                    )
                                                }
                                            }
                                        }
                                        .background(if (index == 0) Orange40 else Color.LightGray)
                                ) {
                                    val drawableId = context.resources.getIdentifier(
                                        item.pic.substringAfter("drawable://"),
                                        "drawable",
                                        context.packageName
                                    )
                                    if (item.isDrawable) {
                                        Image(
                                            painter = painterResource(id = drawableId),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(100.dp)
                                                .clip(CircleShape),
                                        )
                                    } else {
                                        AsyncImage(
                                            model = item.pic, contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(100.dp)
                                                .clip(CircleShape),
                                        )
                                    }
                                }
                                Spacer(modifier = Modifier.size(8.dp))

                                Text(
                                    text = item.name,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))

                    BannersAds(modifier = Modifier.fillMaxWidth(), adSize =  AdSize.LARGE_BANNER)
                }




            }
        }
    )
}



