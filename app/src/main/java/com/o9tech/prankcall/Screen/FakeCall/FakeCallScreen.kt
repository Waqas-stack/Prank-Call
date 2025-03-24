package com.o9tech.prankcall.Screen.FakeCall

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FakeCallScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Fake Call")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = com.o9tech.prankcall.R.drawable.arrowleft),
                            contentDescription = "Back",
                            tint = com.o9tech.prankcall.ui.theme.grey,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            )
        },

        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                }
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun fake() {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }

                },
                title = {
                    Text(text = "Fake Call")
                }
            )
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column (
                    modifier = Modifier.fillMaxSize()
                ){
                    Text(text = "Fake Call Screen")
                }
            }
        }
    )
}