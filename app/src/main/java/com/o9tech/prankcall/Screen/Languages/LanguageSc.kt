package com.o9tech.prankcall.Screen.Languages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.o9tech.prankcall.DataModel.LanguageItem
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.grey
import com.o9tech.prankcall.ui.theme.white

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun LanguageScreen(navController: NavHostController?) {
    val safeNavController = navController ?: rememberNavController()

    val languages = listOf(
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
            TopAppBar(modifier = Modifier
                .fillMaxWidth().background(white),
                navigationIcon = {
                    IconButton(
                        modifier = Modifier.background(white).padding(start = 10.dp),
                        onClick = {
                        safeNavController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowleft),
                            contentDescription = "back",
                            tint = grey,
                        )
                    }
                }, title = {
                    Box(
                        modifier = Modifier.fillMaxSize().background(white),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Language",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.titleLarge,
                            color = Orange40
                        )
                    }
                })
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize().background(white)
                    .padding(it)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize().background(white)
                        .padding(10.dp)
                ) {

                    items(languages.size) { index ->
                        val language = languages[index]
                        Card(
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 10.dp
                            ),
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .padding(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = white
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Box(
                                        contentAlignment = Alignment.Center,
                                        modifier = Modifier
                                            .size(40.dp) // Circle size
                                            .clip(CircleShape)
                                            .background(Color.LightGray) // Placeholder background
                                    ) {
                                        Image(
                                            painter = painterResource(id = language.flag),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .size(40.dp) // Fits inside the circle
                                                .clip(CircleShape)
                                        )
                                    }
                                    Text(
                                        text = language.name,
                                        style = TextStyle(
                                            platformStyle = PlatformTextStyle(
                                                includeFontPadding = false
                                            )
                                        ),
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.W400,
                                        modifier = Modifier.padding(start = 10.dp)
                                    )
                                }
                                RadioButton(
                                    selected = true,
                                    onClick = { /*TODO*/ },
                                    colors = RadioButtonDefaults.colors(
//                                    selectedColor = settingsclr,
                                        selectedColor = Orange40,
                                        unselectedColor = Color.Gray,
                                        disabledSelectedColor = Color.LightGray,
                                        disabledUnselectedColor = Color.DarkGray
                                    )
                                )

                            }
                        }
                    }
                }
            }
        }
    )
}