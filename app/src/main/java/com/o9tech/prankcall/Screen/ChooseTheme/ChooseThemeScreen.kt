package com.o9tech.prankcall.Screen.ChooseTheme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.settingsclr


//@Preview(showBackground = true)
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun ChooseTheme(){
//    Scaffold (
//        topBar = {
//            TopAppBar(
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = settingsclr
//                ),
//                navigationIcon = {
//                    IconButton(onClick = {}) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.arrowleft),
//                            contentDescription = "back",
//                            tint = grey,
//                            modifier = Modifier.padding(start = 10.dp)
//                        )
//                    }
//                },
//
//                title = {
//                    androidx.compose.material3.Text(text = "Choose Theme")
//                }
//            )
//        },
//        content = {
//            Surface(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(it).padding(16.dp)
//            ) {
//                Column(
//                    modifier = Modifier.fillMaxSize()
//                ) {
//
//                }
//            }
//        }
//    )
//}


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.o9tech.prankcall.ui.theme.white


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseThemeScreen(navController: NavHostController?) {

    val safeNavController = navController ?: rememberNavController()

    var selectedTheme by remember { mutableStateOf<ThemeItem?>(null) }

    val themes = listOf(
        ThemeItem(
            Color.Black.copy(alpha = 0.5f),
            Color.Cyan, R.drawable.uk,
            themeName = "Facebook"
        ),
        ThemeItem(
            Color.Red.copy(alpha = 0.5f),
            Color.Magenta,
            R.drawable.turkey,
            themeName = "Instagram"
        ),
        ThemeItem(
            Color.Cyan.copy(alpha = 0.5f),
            Color.LightGray,
            R.drawable.australia,
            themeName = "X-Twitter"
        ),
        ThemeItem(
            Color.Blue.copy(alpha = 0.5f),
            Color.LightGray,
            R.drawable.canada,
            themeName = "Thread"
        ),
        ThemeItem(Color.Gray, Color.LightGray, R.drawable.china, themeName = "youtube"),
    )
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Choose Theme",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        safeNavController.popBackStack()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrowleft),
                            contentDescription = "back",
                            tint = white,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = settingsclr
                ),

                )
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .padding(16.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(themes) { theme ->
                            ThemeBox(
                                theme = theme,
                                themeName = theme.themeName,
                                isSelected = theme == selectedTheme,
                                onThemeSelected = {
                                    selectedTheme = theme
                                })
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ThemeBox(
    theme: ThemeItem, isSelected: Boolean, onThemeSelected: () -> Unit, themeName: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .background(
                theme.boxColor,
                shape = RoundedCornerShape(16.dp)
            )
            .clickable { onThemeSelected() }
            .padding(12.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.videocall),

            contentDescription = null,
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(24.dp)
        )

        Box(
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.Center)
                .background(theme.circleColor, shape = CircleShape), // 🔹 Center Circle Color
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = if (isSelected) R.drawable.done else R.drawable.videocall),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(38.dp)
            )
        }
        Text(
            text = themeName,
            modifier = Modifier.align(Alignment.BottomCenter),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.W400
        )
    }
}
data class ThemeItem(
    val boxColor: Color,
    val circleColor: Color,
    val topIcon: Int,
    val themeName: String = "",
)
