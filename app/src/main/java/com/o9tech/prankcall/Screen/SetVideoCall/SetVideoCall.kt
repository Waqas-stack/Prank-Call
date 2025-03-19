package com.o9tech.prankcall.Screen.SetVideoCall

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.o9tech.prankcall.AppNavigation.Routes
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.grey
import com.o9tech.prankcall.ui.theme.profilecircle
import com.o9tech.prankcall.ui.theme.settingsclr
import com.o9tech.prankcall.ui.theme.tabbg
import com.o9tech.prankcall.ui.theme.white


@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetVideoCallScreen(navController: NavHostController?) {
    val safeNavController = navController ?: rememberNavController()
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    var entername by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var showDialogcall by remember { mutableStateOf(false) }

    val tabTitles = listOf("Incoming call", "calling")
    var selectedTabIndex by remember { mutableStateOf(0) }


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri // Set selected image URI
    }

    Scaffold(
        topBar = {

            TopAppBar(
                title = {
                    Text(
                        "Set Video Call",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 6.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Orange40
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = com.o9tech.prankcall.R.drawable.arrowleft),
                            contentDescription = "back",
                            tint = grey,
                            modifier = Modifier.padding(start = 10.dp)
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
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
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
                            if (selectedImageUri != null) {
                                Image(
                                    painter = rememberAsyncImagePainter(selectedImageUri),
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            } else {
                                Image(
                                    imageVector = Icons.Default.Person,
//                               painter = painterResource(id = R.drawable.uk), // Replace with your image
                                    contentDescription = "Profile Picture",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Crop
                                )
                            }
                            IconButton(
                                onClick = {
                                    imagePickerLauncher.launch("image/*")
                                },
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .offset(
//                                       x = 1.dp,
                                        x = (-5).dp,
                                        y = (-15).dp
                                    )
                                    .background(
//                                       color = Color.Blue,
                                        color = profilecircle,
                                        shape = CircleShape
                                    ).padding(8.dp)
                                    .size(17.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Upload Icon",
                                    tint = settingsclr,

                                    )
                            }
                        }
//                       ProfileImageUploader(onAddImageClick = { /* Handle add image click */ })
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                            .clip(RoundedCornerShape(16.dp))
                    ) {
                        TabRow(
                            selectedTabIndex = selectedTabIndex,
                            containerColor = tabbg,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp),
                            indicator = { tabPositions ->
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .clip(RoundedCornerShape(16.dp))
                                        .then(Modifier.background(Color.White))
                                        .zIndex(-1f)
                                )
                            },
                            divider = {},
//
                        ) {

                            tabTitles.forEachIndexed { index, title ->
                                Tab(
                                    modifier = Modifier.background(if (selectedTabIndex == index) settingsclr else Color.LightGray),
//                                    icon = {
//                                        Icon(
//                                            imageVector = Icons.Default.Person,
//                                            tint = Orange40,
//                                            contentDescription = "Character Name"
//                                        )
//                                    },
                                    selected = selectedTabIndex == index,
                                    onClick = { selectedTabIndex = index },
//                                    onClick = {
////                                        coroutineScope.launch {
//////                                            pagerState.animateScrollToPage(index)
////                                        }
//                                    },
                                    text = {
                                        Row {
                                            Icon(
                                                imageVector = Icons.Default.Call,
//                                                tint = Orange40,
                                                tint = if (selectedTabIndex == index) Color.White else Orange40,
                                                contentDescription = "Character Name"
                                            )
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Text(
                                                text = title,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.W400,
                                                color = if (selectedTabIndex == index) Color.White else Color.Gray
                                            )
                                        }

                                    }

                                )
                            }
                        }
                    }

//                    Row (
//                        modifier = Modifier
//                            .fillMaxWidth(),
//                        verticalAlignment = Alignment.CenterVertically,
//                        horizontalArrangement = Arrangement.Center
//                    ){
//                        Button(onClick = {}) { }
//                        Button(onClick = {}) { }
//                    }
                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Person,
                                tint = Orange40,
                                contentDescription = "Character Name"
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = {
                                entername = ""
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Clear,
                                    tint = Orange40,
                                    contentDescription = "Character Name"
                                )
                            }
                        },
                        singleLine = true,
                        maxLines = 1,
                        shape = RoundedCornerShape(16.dp),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = grey,
                            unfocusedContainerColor = grey,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        value = entername,
                        onValueChange = {
                            entername = it
                        },
                        placeholder = { Text("Enter name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(10.dp))
                            .height(60.dp)
                            .background(grey),

                        ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize().clickable{
                                    safeNavController.navigate(Routes.ChooseThemeScreen)

                                }
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Build,
                                    tint = Orange40,
                                    contentDescription = "Character Name"
                                )
                                Spacer(modifier = Modifier.padding(10.dp))
                                Column {
                                    Text(
                                        text = "Choose theme",
                                        fontWeight = FontWeight.W600,
                                        fontSize = 16.sp
                                    )
                                    Text(text = "Default", fontSize = 12.sp,color = Color.DarkGray)
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    tint = Orange40,
                                    contentDescription = "Character Name"
                                )
                                Spacer(modifier = Modifier.padding(10.dp))
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    tint = Color.DarkGray,
                                    contentDescription = "Character Name"
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(shape = RoundedCornerShape(10.dp))
                            .height(60.dp)
                            .background(grey),

                        ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    showDialog = true
                                }
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    tint = Orange40,
                                    contentDescription = "Character Name"
                                )
                                Spacer(modifier = Modifier.padding(10.dp))
                                Column {
                                    Text(
                                        text = "Set Time",
                                        fontWeight = FontWeight.W600,
                                        fontSize = 16.sp
                                    )
                                    Text(text = "Now",fontSize = 12.sp,color = Color.DarkGray)
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Person,
//                                    tint = Orange40,
//                                    contentDescription = "Character Name"
//                                )
//                                Spacer(modifier = Modifier.padding(10.dp))
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    tint = Color.DarkGray,
                                    contentDescription = "Character Name"
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth().clickable{
                                showDialogcall = true
                            }
                            .clip(shape = RoundedCornerShape(10.dp))
                            .height(60.dp)
                            .background(grey),

                        ) {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Call,
                                    tint = Orange40,
                                    contentDescription = "Character Name"
                                )
                                Spacer(modifier = Modifier.padding(10.dp))
                                Column {
                                    Text(
                                        text = "Call Setting",
                                        fontSize = MaterialTheme.typography.titleMedium.fontSize
                                    )
                                }
                            }
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Person,
//                                    tint = Orange40,
//                                    contentDescription = "Character Name"
//                                )
//                                Spacer(modifier = Modifier.padding(10.dp))
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    tint = Color.DarkGray,
                                    contentDescription = "Character Name"
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {
                            safeNavController.navigate(Routes.FakeVideoCall)

                        },
//                        enabled = TODO(),
//                        shape = TODO(),
//                        colors = TODO(),
//                        elevation = TODO(),
                        border = BorderStroke(1.dp, Color.Red),
//                        contentPadding = TODO(),
//                        interactionSource = TODO()
                    ) {
                        Row {
                            Icon(
//                               imageVector = Icons.Default.KeyboardArrowRight,
                                painter = painterResource(id = com.o9tech.prankcall.R.drawable.videocall),
                                tint = Color.Red,
                                modifier = Modifier.size(24.dp),
                                contentDescription = "Character Name"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Video Call", color = Color.Red)
                        }

                    }
                    CustomRateUsDialogs(
                        showDialog = showDialog,
                        onDismiss = { showDialog = false },
                        onSubmit = { rating ->
                            Log.d("RateUs", "User rated: $rating stars")
                            showDialog = false
                        }
                    )
                    CustomCallSettings(
                        showDialog = showDialogcall,
                        onDismiss = { showDialogcall = false },
//                        onSubmit = { rating ->
//                            Log.d("RateUs", "User rated: $rating stars")
//                            showDialogcall = false
//                        }
                    )
                }
            }
        }
    )
}


@Composable
fun CustomRateUsDialogs(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (Int) -> Unit,
) {
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = white
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Set time delay",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Divider()
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Now",fontSize = 16.sp, fontWeight = FontWeight.Bold)
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
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "5s")
                        RadioButton(
                            selected = false,
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
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "10s")
                        RadioButton(
                            selected = false,
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
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween)
                    {
                        Text(text = "15s")
                        RadioButton(
                            selected = false,
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
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween)
                    {
                        Text(text = "20s")
                        RadioButton(
                            selected = false,
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
                    Row {
                        Button(
                            onClick = { onDismiss() },
                            modifier = Modifier
                                .width(120.dp)
                                .padding(top = 12.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = profilecircle,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Cancel", fontSize = 16.sp)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = { onDismiss()},
                            modifier = Modifier
                                .width(150.dp)
                                .padding(top = 12.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = settingsclr,
                                contentColor = Color.White
                            )
                        ) {
                            Text(text = "Save", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}




@Composable
fun CustomCallSettings(
    showDialog: Boolean,
    onDismiss: () -> Unit,
) {
    val sound = remember { mutableStateOf(false) }
    val vibration = remember { mutableStateOf(false) }
    val flash = remember { mutableStateOf(false) }
    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = white
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 10.dp
                )
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "call Setting",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Divider()
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                tint = Orange40,
                                contentDescription = "Character Name"
                            )
                            Spacer(modifier = Modifier.width(10.dp))

                            Text(text = "Sound")
                        }
                        Switch(
                            modifier = Modifier,
                            checked = sound.value,
                            onCheckedChange = { sound.value = it }
                        )

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                      Row {
                          Icon(
                              imageVector = Icons.Default.Delete,
                              tint = Orange40,
                              contentDescription = "Character Name"
                          )
                          Spacer(modifier = Modifier.width(10.dp))
                          Text(text = "Vibration")
                      }
                        Switch(
                            modifier = Modifier,
                            checked = vibration.value,
                            onCheckedChange = { vibration.value = it }
                        )

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.Default.Build,
                                tint = Orange40,
                                contentDescription = "Character Name"
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "Flash")
                        }
                        Switch(
                            modifier = Modifier,
                            checked = flash.value,
                            onCheckedChange = { flash.value = it }
                        )

                    }
                    }
                }
            }
        }
    }
