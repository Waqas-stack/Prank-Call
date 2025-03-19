package com.o9tech.prankcall.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ShoppingCart
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


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun HomeScreen(navController: NavHostController?) {
    val safeNavController = navController ?: rememberNavController()
//    val gradientColors = listOf(Color.Yellow, Color.Green, Color.Cyan)
    val gradientColors = listOf(Orange40, settingsclr,Purple40)

    val languages = listOf(
        LanguageItem("English", R.drawable.uk),
        LanguageItem("Spanish", R.drawable.germany),
        LanguageItem("Chinese", R.drawable.china),
        LanguageItem("Hindi", R.drawable.india),
        LanguageItem("German", R.drawable.germany),
        LanguageItem("Australian", R.drawable.australia),
        LanguageItem("Saudi Arabia", R.drawable.saudiarabia),
        LanguageItem("United States", R.drawable.usa),
        LanguageItem("Canada", R.drawable.canada),
        LanguageItem("Turkey", R.drawable.turkey),
        LanguageItem("French", R.drawable.germany),
        LanguageItem("UAE", R.drawable.dubai),
    )

    val languages2 = listOf(
        LanguageItem("Spanish", R.drawable.germany),
        LanguageItem("Saudi Arabia", R.drawable.saudiarabia),
        LanguageItem("United States", R.drawable.usa),
        LanguageItem("German", R.drawable.germany),
        LanguageItem("Canada", R.drawable.canada),
        LanguageItem("Turkey", R.drawable.turkey),
        LanguageItem("UAE", R.drawable.dubai),
        LanguageItem("English", R.drawable.uk),
        LanguageItem("French", R.drawable.germany),
        LanguageItem("Hindi", R.drawable.india),
        LanguageItem("Chinese", R.drawable.china),

        LanguageItem("Australian", R.drawable.australia),





    )
    val languages3 = listOf(
        LanguageItem("United States", R.drawable.usa),
        LanguageItem("Canada", R.drawable.canada),
        LanguageItem("Turkey", R.drawable.turkey),
        LanguageItem("UAE", R.drawable.dubai),
        LanguageItem("English", R.drawable.uk),
        LanguageItem("French", R.drawable.germany),
        LanguageItem("Hindi", R.drawable.india),
        LanguageItem("Saudi Arabia", R.drawable.saudiarabia),
        LanguageItem("German", R.drawable.germany),
        LanguageItem("Australian", R.drawable.australia),
        LanguageItem("Chinese", R.drawable.china),
        LanguageItem("Spanish", R.drawable.germany),
    )


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Prank APP",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 28.sp,
                            style = TextStyle(
                                brush = Brush.linearGradient(gradientColors) // Apply Gradient Effect
                            )
                        )
                        Text(text = "Fake Video Call & chat", fontSize = 12.sp)

                    }
                },
                actions = {
                    Row {
                        IconButton(onClick = {
                            safeNavController.navigate(Routes.Setting)
                        }) {
                            Icon(imageVector = Icons.Default.Settings, contentDescription = "Back")
                        }
                        IconButton(onClick = {
                            safeNavController.navigate(Routes.Search)
                        }) {
                            Icon(imageVector = Icons.Default.Star, contentDescription = "Back")
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                ),
            )
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
//                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Back")
                        Icon(
                            painter = painterResource(id = R.drawable.videocall),
                            contentDescription = "Back",
                            tint = Orange40,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Fake Video Call", fontSize = 14.sp)
                    }
                    LazyRow(
                        modifier = Modifier
                            .background(color = Color.White)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(languages.size) { index ->
                            val language = languages[index]
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .padding(8.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(70.dp) // Adjust size as needed
                                        .clip(CircleShape).clickable {
                                            safeNavController.navigate(Routes.AddCharacter)

                                        }
                                        .background(Color.LightGray) // Placeholder background
                                ) {
                                    Image(
//                                        painter = painterResource(id = R.drawable.ic_launcher_background),
                                        painter = painterResource(id = language.flag),
                                        contentDescription = null,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(70.dp) // Ensure it fits inside the circle
                                            .clip(CircleShape)
                                    )
                                }
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
//                                    text = "waqas",
                                    text = language.name,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Divider()
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
//                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Back")
                        Icon(
                            painter = painterResource(id = R.drawable.chat),
                            contentDescription = "Chat",
                            tint = Purple40,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Fake message", fontSize = 14.sp)
                    }
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(languages2.size) {index ->
                            val language = languages2[index]
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(CircleShape).clickable{
                                            safeNavController.navigate(Routes.SetVideoCall)
                                        }
                                        .background(Color.LightGray)
                                ) {
                                    Image(
//                                        painter = painterResource(id = R.drawable.ic_launcher_background),
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
//                                    text = "waqas",
                                    text = language.name,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Divider()
                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
//                        Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Back")
                        Icon(
                            painter = painterResource(id = R.drawable.phone),
                            contentDescription = "call",
                            tint = blue,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "Fake Call", fontSize = 14.sp)
                    }
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(languages3.size) {index ->
                            val language = languages3[index]
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(8.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(70.dp)
                                        .clip(CircleShape).clickable {
                                            safeNavController.navigate(Routes.CallEndedScreen)
                                        }
                                        .background(Color.LightGray)
                                ) {
                                    Image(
//                                        painter = painterResource(id = R.drawable.ic_launcher_background),
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
//                                    text = "waqas",
                                    text = language.name,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                    Divider()

                }
            }
        }
    )
}