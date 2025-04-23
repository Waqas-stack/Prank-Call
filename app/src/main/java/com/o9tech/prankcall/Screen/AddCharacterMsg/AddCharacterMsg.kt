package com.o9tech.prankcall.Screen.AddCharacterMsg


import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.grey
import com.o9tech.prankcall.ui.theme.profilecircle
import com.o9tech.prankcall.ui.theme.settingsclr

import androidx.compose.ui.graphics.*

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.o9tech.prankcall.viewModel.MainViewModel

import kotlinx.coroutines.launch
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCharacterMsg(navController: NavHostController?, mainViewModel: MainViewModel) {

    val safeNavController = navController ?: rememberNavController()
    val scope = rememberCoroutineScope()


    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    var charname by remember { mutableStateOf("") }


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }


    Scaffold(
        topBar = {
            TopAppBar(
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
                    Text(
                        "Add New Character",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 6.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Orange40
                    )
                },
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
                        .padding(16.dp)
                ) {
                    Row (
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ){
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(120.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(color = Color.LightGray, shape = CircleShape)
                            )
                            val selectedFakeImages by mainViewModel.selectedFakeMessage.collectAsState()


                            if (selectedFakeImages.firstOrNull() != null) {
                                val painter = rememberAsyncImagePainter(model = File(selectedFakeImages.first().filepath))

                                Image(
//                                    painter = rememberAsyncImagePainter(selectedImageUri),
                                    painter = rememberAsyncImagePainter(selectedFakeImages.first().uriString),

                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Image(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }




                            IconButton(
                                onClick = {
                                    safeNavController.navigate("fake_message_asset_picker")

                                },
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(
                                        x = (-5).dp,
                                        y = (-15).dp
                                    )
                                    .background(
                                        color = profilecircle,
                                        shape = CircleShape
                                    )
                                    .padding(8.dp)
                                    .size(17.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Upload Icon",
                                    tint = settingsclr,

                                    )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    TextField(
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                tint = settingsclr,
                                contentDescription = "Character Name"
                            )
                        },
                        singleLine = true,
                        maxLines = 1,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = grey,
                            unfocusedContainerColor = grey,
                            cursorColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                        ),
                        value = charname,
                        onValueChange = {
                            charname=it
                        },
                        placeholder = { Text("Name contact") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            if (charname.isNotEmpty() && mainViewModel.selectedFakeMessage.value.firstOrNull() != null) {
                                scope.launch {
                                    mainViewModel.saveFakeMessage(charname)
                                    navController?.popBackStack()
                                }
                            }
                        },
                        border = BorderStroke(1.dp, Color.Red),
                    ) {
                        Text(text = "Save",color = Color.Red)

                    }
                }
            }
        }
    )

}



fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, dashLength: Dp): Modifier =
    this.then(
        Modifier.drawBehind {
            val path = Path()
            val borderSize = strokeWidth.toPx()
            val dashSize = dashLength.toPx()

            path.addRect(Rect(Offset.Zero, size))

            drawPath(
                path,
                color = color,
                style = Stroke(width = borderSize, pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashSize, dashSize), 0f))
            )
        }
    )