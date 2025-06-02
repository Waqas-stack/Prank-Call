package com.o9tech.prankcall.Screen.SettingsSc

import android.content.Context
import android.content.Intent
import android.util.Log
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
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.ads.AdSize
import com.o9tech.prankcall.Add.BannerAds.BannersAds
import com.o9tech.prankcall.AppNavigation.Routes
import com.o9tech.prankcall.DataModel.Languagesis
import com.o9tech.prankcall.R
import com.o9tech.prankcall.ui.theme.blue
import com.o9tech.prankcall.ui.theme.grey
import com.o9tech.prankcall.ui.theme.settingsclr
import com.o9tech.prankcall.ui.theme.white
import com.o9tech.prankcall.utils.PreferenceHelper

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun SettingsScreen(navController: NavHostController?) {
    val safeNavController = navController ?: rememberNavController()
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val preferenceHelper = remember { PreferenceHelper(context) }
    val defaultLang = Languagesis("English", R.drawable.usa,"en")
    val selectedLang = remember { preferenceHelper.getLanguage() ?: defaultLang }


//    val selectedLang = remember { preferenceHelper.getLanguage() }

    Scaffold(
        topBar = {
            TopAppBar(
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
                    Text(
//                        text = "Settings",
                        text = stringResource(R.string.settings),
                        style = TextStyle(
                            platformStyle = PlatformTextStyle(
                                includeFontPadding = false
                            )
                        ),
                        modifier = Modifier
                            .background(white)
                            .padding(start = 10.dp),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = settingsclr
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
                    .fillMaxSize()
                    .background(white)
                    .padding(it)
                    .padding(10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(white)
                ) {


//                    Text(text = "General", fontSize = 16.sp, fontWeight = FontWeight.W600)
                    Text( text = stringResource(R.string.gernal), fontSize = 16.sp, fontWeight = FontWeight.W600)
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .height(300.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = white
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 10.dp
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(10.dp)
                                .background(Color.White),
                            verticalArrangement = Arrangement.SpaceEvenly
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        safeNavController.navigate(Routes.Language)
                                    }
                                    .padding(vertical = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "",
                                        tint = settingsclr

                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
//                                    Text(text = "Languages", fontSize = 16.sp)
                                    Text(text = stringResource(R.string.language), fontSize = 16.sp)
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    selectedLang?.let {
                                        Text(text = it.name, fontSize = 16.sp)
                                        Spacer(modifier = Modifier.width(10.dp))
                                        Box(
                                            contentAlignment = Alignment.Center,
                                            modifier = Modifier
                                                .size(30.dp)
                                                .clip(CircleShape)
                                                .background(Color.LightGray)
                                        ) {
                                            Image(
                                                painter = painterResource(id = it.flag),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .size(30.dp)
                                                    .clip(CircleShape)
                                            )
                                        }
                                    }

                                }
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Info,
                                        contentDescription = "",
                                        tint = settingsclr
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
//                                    Text(text = "Privacy Policy", fontSize = 16.sp)
                                    Text( text = stringResource(R.string.privacy_policy), fontSize = 16.sp)
                                }
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "",
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        shareText(
                                            context,
                                            "Hey! Check out this amazing app: https://play.google.com/store/apps/details?id=com.yourap"
                                        )

                                    }
                                    .padding(vertical = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Default.Share,
                                        contentDescription = "",
                                        tint = settingsclr
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
//                                    Text(text = "Share App", fontSize = 16.sp)
                                    Text( text = stringResource(R.string.share_app), fontSize = 16.sp)
                                }
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "",
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
                                        showDialog = true
                                    }
                                    .padding(vertical = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.Star,
                                        contentDescription = "",
                                        tint = settingsclr
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
//                                    Text(text = "Rate us", fontSize = 16.sp)
                                    Text( text = stringResource(R.string.rate_us), fontSize = 16.sp)
                                }
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "",
                                )
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable {
//                                        safeNavController.navigate(Routes.FakeMessage)
                                    }
                                    .padding(vertical = 5.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row {
                                    Icon(
                                        imageVector = Icons.Outlined.MoreVert,
                                        contentDescription = "",
                                        tint = settingsclr
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
//                                    Text(text = "More APP", fontSize = 16.sp)
                                    Text( text = stringResource(R.string.more_app), fontSize = 16.sp)
                                }
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "",
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                    BannersAds(modifier = Modifier.fillMaxWidth(), adSize =  AdSize.BANNER)
                    CustomRateUsDialog(
                        showDialog = showDialog,
                        onDismiss = { showDialog = false },
                        onSubmit = { rating ->
                            Log.d("RateUs", "User rated: $rating stars")
                            showDialog = false
                        }
                    )
                }
            }
        },
    )
}


fun shareText(context: Context, text: String) {
    val sendIntent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share via")
    context.startActivity(shareIntent)
}


@Composable
fun CustomRateUsDialog(
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
                    Text(text = "😊", fontSize = 48.sp)
                    Text(
//                        text = "Thanks for using Prank App",
                        text = stringResource(R.string.thanks_for_using),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                    Text(
//                        text = "It would be greatly appreciated if you rate us",
                        text = stringResource(R.string.please_rate_us),
                        fontSize = 16.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 8.dp, bottom = 12.dp)
                    )
                    var selectedRating by rememberSaveable { mutableStateOf(0) }
                    Row(horizontalArrangement = Arrangement.Center) {
                        (1..5).forEach { star ->
                            IconButton(onClick = { selectedRating = star }) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Rating Star",
                                    tint = if (star <= selectedRating) settingsclr else grey,
                                    modifier = Modifier.size(40.dp)
                                )   
                            }
                        }
                    }
                    Button(

                        onClick = { onSubmit(selectedRating) },
                        modifier = Modifier
                            .width(200.dp)
                            .padding(top = 12.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = blue,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = stringResource(R.string.rate_now), fontSize = 16.sp)
                    }
                }
            }
        }
    }
}


































