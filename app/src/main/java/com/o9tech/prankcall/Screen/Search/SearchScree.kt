package com.o9tech.prankcall.Screen.Search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.grey


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavHostController?) {
    val safeNavController = navController ?: rememberNavController()

    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                navigationIcon = {
                    IconButton(onClick = {
                        safeNavController.popBackStack()
                    }) {
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
                        placeholder = {
                            Text(
                            text = "Search...",
                            fontSize = 14.sp,

                        ) },
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 12.sp, // Smaller input text
                            color = Color.Black,
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            ),
                        ),
                        leadingIcon = {
                            Icon(
//                                painter = painterResource(id = R.drawable.arrowleft),
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = Color.Black,
//                                modifier = Modifier.size(25.dp).padding(start = 10.dp)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                            .background(
                                Color.LightGray,
                                shape = RoundedCornerShape(28.dp)
                            ), // Rounded Corners
                        shape = RoundedCornerShape(28.dp), // Ensures TextField itself has rounded corners
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = grey, // Background color
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        )
                    )
                },
                actions = {}
            )
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(10.dp)
            ) {
                Column(

                ) {
                    Text(text = "Search Screen", fontSize = 20.sp)
                }
            }
        }
    )
}