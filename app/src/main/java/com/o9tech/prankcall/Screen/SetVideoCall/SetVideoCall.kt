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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
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
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.o9tech.prankcall.ui.theme.Orange40
import com.o9tech.prankcall.ui.theme.grey
import com.o9tech.prankcall.ui.theme.profilecircle
import com.o9tech.prankcall.ui.theme.settingsclr
import com.o9tech.prankcall.ui.theme.textfrilssetvideocall
import com.o9tech.prankcall.ui.theme.white
import kotlinx.coroutines.delay
import android.content.Context
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.google.android.gms.ads.AdSize
import com.o9tech.prankcall.Add.BannerAds.BannersAds
import com.o9tech.prankcall.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetVideoCallScreen(
    navController: NavHostController?,
    name: String,
    flag: String,
    videoPath: String,
) {
    val safeNavController = navController ?: rememberNavController()
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    var entername by remember { mutableStateOf(name) }
    var showDialog by remember { mutableStateOf(false) }
    var showcallDialog by remember { mutableStateOf(false) }
    var selectedTime by remember { mutableStateOf<Int?>(null) }

    var showDialogcall by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()


    var showBlackScreen by remember { mutableStateOf(false) }
    var delayJob by remember { mutableStateOf<Job?>(null) }





//    val tabTitles = listOf("Incoming call", "calling")


    val tabTitles = listOf(stringResource(R.string.incoming_), stringResource(R.string.calling))
    var selectedTabIndex by remember { mutableStateOf(0) }


    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedImageUri = uri
    }

    val drawableId = LocalContext.current.resources.getIdentifier(
        flag.substringAfter("drawable://"),
        "drawable",
        LocalContext.current.packageName
    )

    val systemUiController = rememberSystemUiController()

//    SideEffect {
//        if (showBlackScreen) {
//            systemUiController.setSystemBarsColor(
//                color = Color.Black,
//                darkIcons = false // White icons for dark background
//            )
//        } else {
//            systemUiController.setSystemBarsColor(
//                color = Color.Transparent, // Or your default color
//                darkIcons = true // Dark icons for light background
//            )
//        }
//    }

    Scaffold(
        topBar = {
            if (!showBlackScreen) {
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = Color.Black,
                        darkIcons = true // White icons for dark background
                    )
                }
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.set_video_call),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.padding(start = 6.dp),
                            fontWeight = FontWeight.Bold,
                            color = Orange40
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            safeNavController.popBackStack()
                        }) {
                            Icon(
                                painter = painterResource(id = com.o9tech.prankcall.R.drawable.arrowleft),
                                contentDescription = "back",
                                tint = grey,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White
                    )
                )
            }
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                if (showBlackScreen) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black).pointerInput(Unit){
                                detectTapGestures(
                                    onDoubleTap = {
                                        delayJob?.cancel()
                                        delayJob = null
                                        showBlackScreen = false
                                        selectedTime = null
                                    }
                                )
                            },
                    ) {
                        Text(
                            text = stringResource(R.string.double_tap_exit),
                            color = Color.White,
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 32.dp),
                            fontSize = 16.sp
                        )
                    }
                }else{
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = Color.White)
                            .padding(16.dp)
                    ) {
                        BannersAds(modifier = Modifier.fillMaxWidth(), adSize =  AdSize.LARGE_BANNER)
                        Spacer(modifier = Modifier.height(25.dp))
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
                                        .background(color = textfrilssetvideocall, shape = CircleShape)
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


                                    if (drawableId != 0) {
                                        Image(
                                            painter = painterResource(id = drawableId),
                                            contentDescription = "Profile Picture",
                                            modifier = Modifier
                                                .size(100.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                    } else {
                                        AsyncImage(
                                            model = flag,
                                            contentDescription = "Profile Picture",
                                            modifier = Modifier
                                                .size(100.dp)
                                                .clip(CircleShape),
                                            contentScale = ContentScale.Crop
                                        )
                                    }
                                }

//                            IconButton(
//                                onClick = {
//                                    imagePickerLauncher.launch("image/*")
//                                },
//                                modifier = Modifier
//                                    .align(Alignment.BottomEnd)
//                                    .offset(x = (-5).dp, y = (-15).dp)
//                                    .background(color = profilecircle, shape = CircleShape)
//                                    .padding(8.dp)
//                                    .size(17.dp)
//                            ) {
//                                Icon(
//                                    imageVector = Icons.Default.Add,
//                                    contentDescription = "Upload Icon",
//                                    tint = settingsclr,
//                                )
//                            }

                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                                .clip(RoundedCornerShape(16.dp))
                        ) {
                            TabRow(
                                selectedTabIndex = selectedTabIndex,
                                containerColor = textfrilssetvideocall,
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
                            ) {

                                tabTitles.forEachIndexed { index, title ->
                                    Tab(
                                        modifier = Modifier.background(if (selectedTabIndex == index) settingsclr else textfrilssetvideocall),
                                        selected = selectedTabIndex == index,
                                        onClick = { selectedTabIndex = index },
                                        text = {
                                            Row {
                                                Icon(
                                                    imageVector = Icons.Default.Call,
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
                        Spacer(modifier = Modifier.height(10.dp))

                        TextField(
                            readOnly = true,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    tint = Orange40,
                                    contentDescription = "Character Name"
                                )
                            },
                            trailingIcon = {
                                IconButton(onClick = {
//                                entername = ""
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
                                focusedContainerColor = textfrilssetvideocall,
                                unfocusedContainerColor = textfrilssetvideocall,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            value = entername,
                            onValueChange = {
//                            entername = it
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
                                .background(textfrilssetvideocall),

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
//                                        text = "Set Time",
                                            text = stringResource(R.string.set_time),
                                            fontWeight = FontWeight.W600,
                                            fontSize = 16.sp
                                        )
//                                    Text(text = "Now", fontSize = 12.sp, color = Color.DarkGray)
                                        Text(text = stringResource(R.string.now), fontSize = 12.sp, color = Color.DarkGray)
                                    }
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
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
                                if (selectedTime != null) {
                                    showcallDialog = true
                                } else {
                                    Log.d("selectedTabIndex ", "${selectedTabIndex}")
                                    when (selectedTabIndex) {
                                        0 -> {

                                            val videospath = Uri.encode(videoPath)

                                            val encodedPicPath = Uri.encode(flag)

                                            safeNavController.navigate("IncommingCallScreen/$name/$encodedPicPath/$videospath")


                                        }

                                        else -> {
                                            val encodedPicPath = Uri.encode(flag)
                                            val videospath = Uri.encode(videoPath)

                                            safeNavController.navigate("VideoCallingScreen/$name/$encodedPicPath/$videospath")

                                        }
                                    }

                                }

                            },
                            border = BorderStroke(1.dp, settingsclr),
                        ) {
                            Row {
                                Icon(
                                    painter = painterResource(id = com.o9tech.prankcall.R.drawable.videocall),
                                    tint = settingsclr,
                                    modifier = Modifier.size(24.dp),
                                    contentDescription = "Character Name"
                                )
                                Spacer(modifier = Modifier.width(10.dp))
//                            Text(text = "Video Call", color = settingsclr)
                                Text(  text = stringResource(R.string.video_call), color = settingsclr)
                            }

                        }
                        SetTimeDelay(
                            showDialog = showDialog,
                            onDismiss = { showDialog = false },
                            onSubmit = { time ->
                                selectedTime = time
                            }
                        )


                            SetGoingToCall(
                                showDialog = showcallDialog,
                                delayInSec = selectedTime ?: 0,

                                onDismiss = {
                                    showcallDialog = false
                                    showBlackScreen = true
                                    selectedTime?.let { delayInSec ->
                                        delayJob = scope.launch {
                                            delay(delayInSec * 1000L)
                                            showBlackScreen = false

                                            val videospath = Uri.encode(videoPath)
                                            val encodedPicPath = Uri.encode(flag)
                                            safeNavController.navigate("IncommingCallScreen/$name/$encodedPicPath/$videospath")
    //                                        Toast.makeText(context, "Hello Waqas", Toast.LENGTH_SHORT).show()
                                            selectedTime = null
                                        }
                                    }
                                }
                            )
                        CustomCallSettings(
                            showDialog = showDialogcall,
                            onDismiss = { showDialogcall = false },
                        )
                    }
                }


            }
        }
    )
}


@Composable
fun SetTimeDelay(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onSubmit: (Int) -> Unit,
) {
    if (showDialog) {
        val selectedTime = remember { mutableStateOf(0) }
        Dialog(onDismissRequest = onDismiss) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = white),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    Text("Set time delay", fontSize = 18.sp, fontWeight = FontWeight.W600, textAlign = TextAlign.Center)
                    Text( text = stringResource(R.string.set_time_delay), fontSize = 18.sp, fontWeight = FontWeight.W600, textAlign = TextAlign.Center)
                    Divider()

                    val options = listOf(0, 5, 10, 15, 20)

                    options.forEach { time ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = if (time == 0) stringResource(R.string.now) else "${time}s")
                            RadioButton(
                                selected = selectedTime.value == time,
                                onClick = { selectedTime.value = time },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Orange40,
                                    unselectedColor = Color.Gray,
                                    disabledSelectedColor = Color.LightGray,
                                    disabledUnselectedColor = Color.DarkGray
                                )
                            )
                        }
                    }

                    Row {
                        Button(
                            onClick = onDismiss,
                            modifier = Modifier
                                .width(120.dp)
                                .padding(top = 12.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = profilecircle)
                        ) {
//                            Text("Cancel", fontSize = 16.sp)
                            Text( text = stringResource(R.string.cancle), fontSize = 16.sp)
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Button(
                            onClick = {
                                onSubmit(selectedTime.value)
                                onDismiss()
                            },
                            modifier = Modifier
                                .width(150.dp)
                                .padding(top = 12.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = settingsclr)
                        ) {
//                            Text("Save", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text( text = stringResource(R.string.save), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun SetGoingToCall(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    delayInSec: Int,
) {
    if (showDialog) {

        Dialog(onDismissRequest = onDismiss) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = white),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
//                    Text("we make call after $delayInSec sec please dont trun off your phone", fontSize = 18.sp, fontWeight = FontWeight.W600, textAlign = TextAlign.Center)
                    Text(text = stringResource(R.string.call_delay_message, delayInSec), fontSize = 18.sp, fontWeight = FontWeight.W600, textAlign = TextAlign.Center)
                    Spacer(modifier = Modifier.height(15.dp))
                    Button(
                        onClick = {
//                            onSubmit(selectedTime.value)
                            onDismiss()
                        },
                        modifier = Modifier
                            .width(150.dp)
                            .padding(top = 12.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = settingsclr)
                    ) {
//                            Text("Got it", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                            Text(   text = stringResource(R.string.got_it), fontSize = 16.sp, fontWeight = FontWeight.Bold)
//                        Text( text = stringResource(R.string.save), fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
//                    Text( text = stringResource(R.string.set_time_delay), fontSize = 18.sp, fontWeight = FontWeight.W600, textAlign = TextAlign.Center)
//                    Divider()

//                    val options = listOf(0, 5, 10, 15, 20)
//
//                    options.forEach { time ->
//                        Row(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(horizontal = 10.dp),
//                            horizontalArrangement = Arrangement.SpaceBetween,
//                            verticalAlignment = Alignment.CenterVertically
//                        ) {
//                            Text(text = if (time == 0) stringResource(R.string.now) else "${time}s")
//                            RadioButton(
//                                selected = selectedTime.value == time,
//                                onClick = { selectedTime.value = time },
//                                colors = RadioButtonDefaults.colors(
//                                    selectedColor = Orange40,
//                                    unselectedColor = Color.Gray,
//                                    disabledSelectedColor = Color.LightGray,
//                                    disabledUnselectedColor = Color.DarkGray
//                                )
//                            )
//                        }
//                    }

//                    Row {
//                        Button(
//                            onClick = onDismiss,
//                            modifier = Modifier
//                                .width(120.dp)
//                                .padding(top = 12.dp),
//                            shape = RoundedCornerShape(10.dp),
//                            colors = ButtonDefaults.buttonColors(containerColor = profilecircle)
//                        ) {
////                            Text("Cancel", fontSize = 16.sp)
//                            Text( text = stringResource(R.string.cancle), fontSize = 16.sp)
//                        }
//                        Spacer(modifier = Modifier.width(10.dp))
//                        Button(
//                            onClick = {
//                                onSubmit(selectedTime.value)
//                                onDismiss()
//                            },
//                            modifier = Modifier
//                                .width(150.dp)
//                                .padding(top = 12.dp),
//                            shape = RoundedCornerShape(10.dp),
//                            colors = ButtonDefaults.buttonColors(containerColor = settingsclr)
//                        ) {
////                            Text("Save", fontSize = 16.sp, fontWeight = FontWeight.Bold)
//                            Text( text = stringResource(R.string.save), fontSize = 16.sp, fontWeight = FontWeight.Bold)
//                        }
//                    }
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
    val context = LocalContext.current
    val sound = remember { mutableStateOf(false) }
    val vibration = remember { mutableStateOf(false) }
    val flash = remember { mutableStateOf(false) }

    if (showDialog) {
        Dialog(onDismissRequest = onDismiss) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = white),
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .height(210.dp)
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "Call Settings",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))

                    // Sound
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Icon(Icons.Default.Notifications, contentDescription = null, tint = Orange40)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text("Sound")
                        }
                        Switch(
                            modifier = Modifier.size(20.dp, 15.dp),
                            checked = sound.value,
                            onCheckedChange = { sound.value = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = white,
                                checkedTrackColor = settingsclr,
                                uncheckedThumbColor = white,
                                uncheckedTrackColor = Color.LightGray
                            )
                        )
                    }

                    // Vibration
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Icon(Icons.Default.Delete, contentDescription = null, tint = Orange40)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text("Vibration")
                        }
                        Switch(
                            modifier = Modifier.size(20.dp, 15.dp),
                            checked = vibration.value,
                            onCheckedChange = {
                                vibration.value = it
                                if (it) triggerVibration(context)
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = white,
                                checkedTrackColor = settingsclr,
                                uncheckedThumbColor = white,
                                uncheckedTrackColor = Color.LightGray
                            )
                        )
                    }

                    // Flash
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row {
                            Icon(Icons.Default.Build, contentDescription = null, tint = Orange40)
                            Spacer(modifier = Modifier.width(10.dp))
                            Text("Flash")
                        }
                        Switch(
                            modifier = Modifier.size(20.dp, 15.dp),
                            checked = flash.value,
                            onCheckedChange = { flash.value = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = white,
                                checkedTrackColor = settingsclr,
                                uncheckedThumbColor = white,
                                uncheckedTrackColor = Color.LightGray
                            )
                        )
                    }
                }
            }
        }
    }
}

// Helper function to trigger vibration
fun triggerVibration(context: Context) {
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (vibrator.hasVibrator()) {
        val vibrationEffect = VibrationEffect.createOneShot(300, VibrationEffect.DEFAULT_AMPLITUDE)
        vibrator.vibrate(vibrationEffect)
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewSetVideoCallScreen() {
    SetVideoCallScreen(
        navController = rememberNavController(),
        name = "John Doe",
        flag = "drawable://img_home_messi",
        videoPath = "path/to/video.mp4"
    )
}
