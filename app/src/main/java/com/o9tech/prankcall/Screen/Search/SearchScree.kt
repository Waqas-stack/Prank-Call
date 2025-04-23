package com.o9tech.prankcall.Screen.Search

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.o9tech.prankcall.DataModel.FakeMessage
import com.o9tech.prankcall.DataModel.LanguageItem
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.grey
import com.o9tech.prankcall.ui.theme.greytext


//@Preview(showBackground = true)
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun SearchScreen(navController: NavHostController?) {
//    val safeNavController = navController ?: rememberNavController()
//
//    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(10.dp),
//                navigationIcon = {
//                    IconButton(onClick = {
//                        safeNavController.popBackStack()
//                    }) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.arrowleft),
//                            contentDescription = "back",
//                            tint = grey,
//                            modifier = Modifier.padding(start = 10.dp)
//                        )
//                    }
//                },
//                title = {
//                    TextField(
//                        value = searchQuery,
//                        onValueChange = { searchQuery = it },
//                        placeholder = {
//                            Text(
//                            text = "Search...",
//                            fontSize = 14.sp,
//
//                        ) },
//                        singleLine = true,
//                        textStyle = TextStyle(
//                            fontSize = 12.sp, // Smaller input text
//                            color = Color.Black,
//                            platformStyle = PlatformTextStyle(
//                                includeFontPadding = false
//                            ),
//                        ),
//                        leadingIcon = {
//                            Icon(
////                                painter = painterResource(id = R.drawable.arrowleft),
//                                imageVector = Icons.Default.Search,
//                                contentDescription = "Search",
//                                tint = Color.Black,
////                                modifier = Modifier.size(25.dp).padding(start = 10.dp)
//                            )
//                        },
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .height(50.dp)
//                            .background(
//                                Color.LightGray,
//                                shape = RoundedCornerShape(28.dp)
//                            ), // Rounded Corners
//                        shape = RoundedCornerShape(28.dp), // Ensures TextField itself has rounded corners
//                        colors = TextFieldDefaults.textFieldColors(
//                            containerColor = grey, // Background color
//                            focusedIndicatorColor = Color.Transparent,
//                            unfocusedIndicatorColor = Color.Transparent
//                        )
//                    )
//                },
//                actions = {}
//            )
//        },
//        content = {
//            Surface(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(it)
//                    .padding(10.dp)
//            ) {
//                Column(
//
//                ) {
//                    Text(text = "Search Screen", fontSize = 20.sp)
//
//                }
//            }
//        }
//    )
//}








@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun SearchScreen(navController: NavHostController?) {
    val safeNavController = navController ?: rememberNavController()
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val itemList = listOf("Apple", "Banana", "Cherry", "Date", "Elderberry", "Fig", "Grapes", "Honeydew", "Ice Plant", "Jackfruit")

    val filteredList = itemList.filter { it.contains(searchQuery.text, ignoreCase = true) }

    val fakeMessage = listOf(
        LanguageItem("English", R.drawable.uk),
        LanguageItem("French", R.drawable.germany),
        LanguageItem("Chinese", R.drawable.china),
        LanguageItem("Hindi", R.drawable.india),
        LanguageItem("Australian", R.drawable.australia),
        LanguageItem("Spanish", R.drawable.germany),
        LanguageItem("Saudi Arabia", R.drawable.saudiarabia),
        LanguageItem("United States", R.drawable.usa),
        LanguageItem("German", R.drawable.germany),
        LanguageItem("Canada", R.drawable.canada),
        LanguageItem("Turkey", R.drawable.turkey),
        LanguageItem("UAE", R.drawable.dubai),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                navigationIcon = {
                    IconButton(onClick = { safeNavController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowleft),
                            contentDescription = "back",
                            tint = grey,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                },
                title = {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search...", fontSize = 12.sp) },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 12.sp,
                            color = Color.Black,
                            platformStyle = PlatformTextStyle(includeFontPadding = false)
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.Black
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(greytext, shape = RoundedCornerShape(28.dp)),
                        shape = RoundedCornerShape(28.dp),
                        colors = TextFieldDefaults.colors(
                        ).copy(
                            unfocusedContainerColor = greytext,
                            focusedContainerColor = greytext,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                },
                actions = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize().background(color = Color.White)
                    .padding(it)
                    .padding(10.dp)
            ) {
                if (searchQuery.text.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.background(color = Color.White).fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(fakeMessage.size) { item ->
                            val item = fakeMessage[item]


                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(80.dp)
                                        .clip(CircleShape)
                                        .background(Color.LightGray)
                                ) {
                                    Image(
                                        painter = painterResource(id = item.flag),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(80.dp)
                                            .clip(CircleShape)
                                    )
                                }
                                Text(
                                    text = item.name,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                } else {
                    Text(
                        text = "Start typing to search...",
                        fontSize = 16.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    )
}
