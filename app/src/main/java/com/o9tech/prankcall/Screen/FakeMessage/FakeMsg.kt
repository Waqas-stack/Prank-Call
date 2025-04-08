package com.o9tech.prankcall.Screen.FakeMessage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.o9tech.prankcall.AppNavigation.Routes
import com.o9tech.prankcall.DataModel.FakeMessage
import com.o9tech.prankcall.DataModel.LanguageItem
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.PurpleGrey80
import com.o9tech.prankcall.viewModel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FakeMessageScreen(navController: NavHostController?, mainViewModel: MainViewModel) {
    val safeNavController = navController ?: rememberNavController()

//    val fakeMessagess by mainViewModel.fakeMessage.collectAsState()

    val fakeMessages = mainViewModel.fakeMessage.collectAsState().value

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
    //----------------
//    val fakeMessage = listOf(
//        LanguageItem("English", R.drawable.img_get_started_cha_eunwoo),
//
//        LanguageItem("jennie", R.drawable.img_home_carrdi),
//        LanguageItem("jisoo", R.drawable.img_home_iu),
//        LanguageItem("Messi", R.drawable.img_home_messi),
//        LanguageItem("Ronaldo", R.drawable.img_get_started_ronadol),
//    )
    //--------------

    val predefinedItems = listOf(
//        FakeMessage("English", "drawable://img_get_started_cha_eunwoo",false),
        FakeMessage("Jennie", "drawable://img_home_carrdi",true),
        FakeMessage("Jisoo", "drawable://img_home_iu",true),
        FakeMessage("Messi", "drawable://img_home_messi",true),
        FakeMessage("Ronaldo", "drawable://img_get_started_ronadol",true),
    )
    val context= LocalContext.current

    // Combining the database data with the predefined list
    val combinedFakeMessages = fakeMessages.map {
        FakeMessage(it.name, it.imageUri,false)
    } + predefinedItems
        
        
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Fake message",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 6.dp),
//                        style = MaterialTheme.typography.bodyLarge,
                        color = Orange40,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(onClick = {
                        safeNavController.navigate(Routes.Search)
                    }) {
                        Icon(imageVector = Icons.Default.Search,
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
                    modifier = Modifier.fillMaxSize().background(color = Color.White).padding(5.dp),
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
                                                safeNavController.navigate(Routes.AddCharacterMsg)
                                            }
                                            else -> {
                                                safeNavController.navigate(Routes.OverlappingBoxWithRoundedCorners)
                                            }
                                        }
                                    }
                                    .background(if (index == 0) Orange40 else Color.LightGray) // Conditional background color
                            ) {

                                val drawableId = context.resources.getIdentifier(
                                    item.pic.substringAfter("drawable://"),
                                    "drawable",
                                    context.packageName
                                )

                              if (index == 0) {
                                   Image(
                                       painterResource(id = R.drawable.plus),
                                       colorFilter = if (index == 0) ColorFilter.tint(Color.White) else null,
                                       contentDescription = null,
                                   )
                                } else {
                                    if(item.isDrawable){
                                        Image(
                                            painter =painterResource(id = drawableId),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(100.dp) // Fits inside the circle
                                                .clip(CircleShape),
                                            colorFilter = if (index == 0) ColorFilter.tint(Color.White) else null
                                        )
                                    }
                                    else{
                                        AsyncImage(model = item.pic, contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(100.dp) // Fits inside the circle
                                                .clip(CircleShape),)
                                    }
                                }

                            }

                            Spacer(modifier = Modifier.size(8.dp))

                            Text(
                                text = if (index == 0) "Add New" else item.name,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

//                LazyVerticalGrid(
//                    columns = GridCells.Fixed(3), // 3 columns
//                    modifier = Modifier.fillMaxSize().background(color = Color.White).padding(5.dp),
//                    verticalArrangement = Arrangement.spacedBy(16.dp),
//                    horizontalArrangement = Arrangement.spacedBy(0.dp)
//                ) {
//                    itemsIndexed(fakeMessage) { index,item ->
////                        val item = fakeMessage[item]
////                        CircularImageWithText(imageRes = item.first, text = item.second)
//                        Column(
//                            horizontalAlignment = Alignment.CenterHorizontally,
//                            modifier = Modifier.padding(8.dp)
//                        ) {
//                            Box(
//                                contentAlignment = Alignment.Center,
//                                modifier = Modifier
//                                    .size(100.dp) // Circle size
//                                    .clip(CircleShape).clickable{
//                                        when (index) {
//                                            0 -> {
//                                                safeNavController.navigate(Routes.AddCharacterMsg)
//                                            }
//                                            else  -> {
//                                                safeNavController.navigate(Routes.OverlappingBoxWithRoundedCorners)
//                                        }
//                                        }
////                                        safeNavController.navigate(Routes.AddCharacter)
//
//                                    }
//                                    .background(if (index == 0) Orange40 else Color.LightGray) // Conditional background color
//
//                            ) {
//                                val imagePainter = if (index == 0) {
//                                    painterResource(id = R.drawable.plus)
//                                } else {
//                                    painterResource(id = item.flag)
//                                }
//                                Image(
////                                    painter = painterResource(id = item.flag),
//                                    painter = imagePainter,
//                                    contentDescription = null,
//                                    contentScale = ContentScale.Crop,
//
//                                    modifier = Modifier
//                                        .size(100.dp) // Fits inside the circle
//                                        .clip(CircleShape),
////                                    tint = if (index == 0) Color.White else white,
//                                    colorFilter = if (index == 0) ColorFilter.tint(Color.White) else null
//
//                                )
//                            }
////                            Spacer(modifier = Modifier.height(8.dp))
//                            Spacer(modifier = Modifier.size(8.dp))
//                            Text(
////                                text = item.name,
//                                text = if (index == 0) "Add New" else item.name,
//                                fontSize = 14.sp,
//                                fontWeight = FontWeight.Bold,
//                                textAlign = TextAlign.Center
//                            )
//                        }
//                    }
//                }
            }
        }
    )
}



