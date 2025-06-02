package com.o9tech.prankcall.Screen.AddNewCharacter

import android.Manifest
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import android.widget.VideoView
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.compose.ui.unit.sp
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.android.gms.ads.AdSize
import com.huhx.picker.model.AssetInfo
import com.huhx.picker.model.AssetPickerConfig
import com.huhx.picker.model.RequestType
import com.huhx.picker.support.PickerPermissions
import com.huhx.picker.view.AssetPicker
import com.o9tech.prankcall.Add.BannerAds.BannersAds
import com.o9tech.prankcall.ui.theme.white
import com.o9tech.prankcall.viewModel.MainViewModel

import kotlinx.coroutines.launch
import java.io.File


@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun AddCharacterSCreen(navController: NavHostController?, mainViewModel: MainViewModel) {

    val safeNavController = navController ?: rememberNavController()
    val scope = rememberCoroutineScope()
    var videoView: VideoView? = null



    val context = LocalContext.current
    var charname by rememberSaveable { mutableStateOf("") }

    val imageUri = remember { mutableStateOf<Uri?>(null) }
    val videoUri = remember { mutableStateOf<Uri?>(null) }
    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri.value = uri
    }

    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        videoUri.value = uri
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
//                        "Add New Character",
                        text = stringResource(R.string.add_new_character),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 6.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Orange40
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = white
                ),
            )
        },
        content = {
            Surface(
                modifier = Modifier
                    .fillMaxSize().background(Color.White)
                    .padding(it)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize().background(Color.White)
                        .padding(16.dp)
                ) {
                    Spacer(modifier = Modifier.height(5.dp))
                    BannersAds(modifier = Modifier.fillMaxWidth(), adSize =  AdSize.LARGE_BANNER)
                    Spacer(modifier = Modifier.height(15.dp))
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
                           imageUri.value?.let {
                               AsyncImage(
                                   model = it,
                                   contentDescription = null,
                                   modifier = Modifier
                                       .size(120.dp)
                                       .clip(CircleShape),
                                   contentScale = ContentScale.Crop
                               )
                           } ?: Icon(
                               imageVector = Icons.Default.Person,
                               contentDescription = "Default Person Icon",
                               modifier = Modifier
                                   .size(120.dp)
                                   .clip(CircleShape)
                                   .padding(16.dp),
                               tint = Color.DarkGray
                           )
                           IconButton(
                              onClick = {
                                  imagePickerLauncher.launch("image/*")
                              },
                              modifier = Modifier
                                  .align(Alignment.BottomEnd)
                                  .offset(
                                      x = 2.dp,
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
                    Text(
//                        "Character's name",
                        text = stringResource(R.string.character_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 6.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Orange40
                    )
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
//                        placeholder = { Text("Character Name") },
                        placeholder = { Text(text = stringResource(R.string.character_name),) },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
//                        "Video call ",
                        text = stringResource(R.string.video_call),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(start = 6.dp),
                        style = MaterialTheme.typography.titleMedium,
                        color = Orange40
                    )
                    Spacer(modifier = Modifier.height(15.dp))
                    Box(
                        modifier = Modifier
                            .size(120.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .dashedBorder(2.dp, settingsclr, 8.dp)
                            .clickable {
                                videoPickerLauncher.launch("video/*")
                            },
                        contentAlignment = Alignment.Center

                    ) {
                        if (videoUri.value != null) {
                            AndroidView(
                                factory = { context ->
                                    VideoView(context).apply {
                                        setVideoURI(videoUri.value)
                                        setOnPreparedListener { mediaPlayer ->
                                            mediaPlayer.isLooping = true
                                            start()
                                        }
                                        videoView = this
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clip(RoundedCornerShape(12.dp))
                                    .border(2.dp, Color.Gray, RoundedCornerShape(12.dp))
                            )

                            DisposableEffect(Unit) {
                                onDispose {
                                    videoView?.stopPlayback()
                                    videoView = null
                                }
                            }
                        } else {
                            Column(
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "Add Video",
                                    modifier = Modifier.size(48.dp),
                                    tint = Color.Gray
                                )
                                Text(
//                                    text = "Upload Videos",
                                    text = stringResource(R.string.upload_videos),
                                    color = Color.Gray,
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    TextButton(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = {

                            val imagePath = imageUri.value?.let {
                                copyUriToInternalStorage(context, it, "image_${System.currentTimeMillis()}.jpg")
                            } ?: ""

                            val videoPath = videoUri.value?.let {
                                copyUriToInternalStorage(context, it, "video_${System.currentTimeMillis()}.mp4")
                            } ?: ""

                            Log.d("Video", "VideoPath: $videoPath")
                            Log.d("imagePath", "ImagePath: $imagePath")

                            if (charname.isNotEmpty() && imagePath.isNotEmpty() && videoPath.isNotEmpty()) {
                                scope.launch {
                                    mainViewModel.insertUserDetails(charname, imagePath, videoPath)
                                    navController?.popBackStack()
                                }
                            } else {
                                Toast.makeText(context, "Character name cannot be empty", Toast.LENGTH_SHORT).show()
                            }
                        },
                        border = BorderStroke(1.dp, Color.Red),

                    ) {
//                        Text(text = "Save",color = Color.Red)
                        Text(text = stringResource(R.string.save),color = Color.Red)

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




@Composable
fun ImagePicker(
    onPicked: (List<AssetInfo>) -> Unit,
    onClose: (List<AssetInfo>) -> Unit,
) {
    PickerPermissions(permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)) {
        AssetPicker(
            assetPickerConfig = AssetPickerConfig(maxAssets = 1, gridCount = 3, requestType = RequestType.IMAGE),
            onPicked = onPicked,
            onClose = onClose
        )
    }
}


@Composable
fun VideoPicker(
    onPicked: (List<AssetInfo>) -> Unit,
    onClose: (List<AssetInfo>) -> Unit,
) {
    PickerPermissions(permissions = listOf(Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)) {
        AssetPicker(
            assetPickerConfig = AssetPickerConfig(maxAssets = 1, gridCount = 3, requestType = RequestType.VIDEO),
            onPicked = onPicked,
            onClose = onClose
        )
    }
}

fun copyUriToInternalStorage(context: Context, uri: Uri, filename: String): String {
    val inputStream = context.contentResolver.openInputStream(uri)
    val file = File(context.filesDir, filename)
    inputStream?.use { input ->
        file.outputStream().use { output ->
            input.copyTo(output)
        }
    }
    return file.absolutePath
}






