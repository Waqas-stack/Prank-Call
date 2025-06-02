package com.o9tech.prankcall.Screen.callended

import android.app.Activity
import android.content.Context
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.o9tech.prankcall.R
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.o9tech.prankcall.Screen.SettingsSc.CustomRateUsDialog


@Composable
fun CallEndedScreen(
    navController: NavHostController?,
    profileImage: String,
    onReturn: () -> Unit,
    onCallAgain: () -> Unit
) {
    val safeNavController = navController ?: rememberNavController()
    val context = LocalContext.current
    val activity = context as? Activity


    var loadInterstitialAd by remember { mutableStateOf(true) }

    var interstitialAd: InterstitialAd? by remember { mutableStateOf(null) }
    var shouldShowAd by remember { mutableStateOf(false) }



    LaunchedEffect(loadInterstitialAd) {
        if(loadInterstitialAd){
            InterstitialAd.load(
                context,
                "ca-app-pub-3940256099942544/1033173712",
                AdRequest.Builder().build(),
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(error: LoadAdError) {
                        println("Ad failed to load: ${error.message}")
                    }
                    override fun onAdLoaded(loadedAd: InterstitialAd) {
                        println("Ad Loaded Successfully")
                        interstitialAd = loadedAd
                    }
                }
            )
        }
    }


//    LaunchedEffect(interstitialAd != null) {
//        loadInterstitialAd = false
//        if (interstitialAd != null) {
//            interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
//                override fun onAdDismissedFullScreenContent() {
//                    super.onAdDismissedFullScreenContent()
//                    println("Ad dismissed, resetting value...")
//                    interstitialAd = null
//                    loadInterstitialAd=false
//                }
//
//                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
//                    println("Ad failed to show: ${adError.message}")
//                    interstitialAd = null
//                }
//            }
//            activity?.let {
//                interstitialAd?.show(it)
//            }
//        }
//    }




    if (shouldShowAd) {
        loadAd(context) { ad ->
            interstitialAd = ad
            ad.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    interstitialAd = null
                    shouldShowAd = false
                    safeNavController.popBackStack() // Navigate back
                }

                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                    interstitialAd = null
                    shouldShowAd = false
                }
            }
            activity?.let { ad.show(it) }
        }
    }


    val drawableId = LocalContext.current.resources.getIdentifier(
        profileImage.substringAfter("drawable://"),
        "drawable",
        LocalContext.current.packageName
    )
    Scaffold (
       content = {
           Surface(
               modifier = Modifier
                   .fillMaxSize()
                   .background(color = Color.White)
                   .padding(it)
           ) {
               Box(
                   modifier = Modifier
                       .fillMaxSize()
                       .background(Color.Black)
                       .paint(
                           if (drawableId != 0)
                               painterResource(id = drawableId) else rememberAsyncImagePainter(profileImage),
                           contentScale = ContentScale.Crop,
                           alpha = 0.22f
                       )
               ) {
//                   IconButton(
//                       modifier = Modifier.align(Alignment.TopStart).padding(16.dp),
//                       onClick = {
//                           safeNavController.popBackStack()
//                       }) {
//                       Icon(
//                           painter = painterResource(id = R.drawable.arrowleft),
//                           contentDescription = "Back",
//                           tint = Color.White,
//                           modifier = Modifier.size(24.dp))
//                   }
                   Column(
                       modifier = Modifier
                           .fillMaxSize()
                           .padding(24.dp),
                       horizontalAlignment = Alignment.CenterHorizontally,
                       verticalArrangement = Arrangement.SpaceBetween
                   ) {
                       Column (
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center
                       ){
                           Box(
                               contentAlignment = Alignment.Center
                           ) {
                               if (drawableId != 0) {
                               Image(
                                   painter = painterResource(id = drawableId),
                                   contentDescription = "Profile Picture",
                                   modifier = Modifier
                                       .size(90.dp)
                                       .clip(CircleShape)
                                       .border(4.dp, Color.White, CircleShape)
                               )}else{
                                   AsyncImage(
                                       model = profileImage,
                                       contentDescription = "Profile Picture",
                                       modifier = Modifier
                                           .size(90.dp)
                                           .clip(CircleShape)
                                           .border(4.dp, Color.White, CircleShape)
                                   )
                               }
                               Box(
                                   modifier = Modifier
                                       .size(100.dp)
                                       .clip(CircleShape)
                                       .background(Color.Transparent.copy(alpha = 0.5f))
                                       .align(Alignment.TopEnd)
                               )
                           }
                           Text(
//                               text = "Call ended",
                               text = stringResource(R.string.Call_ended),
                               fontSize = 22.sp,
                               fontWeight = FontWeight.W400,
                               color = Color.White
                           )
                           Text(
                               text = "00:14",
                               fontSize = 14.sp,
                               color = Color.White.copy(alpha = 0.7f)
                           )
                       }
                       Column (
                           horizontalAlignment = Alignment.CenterHorizontally,
                           verticalArrangement = Arrangement.Center
                       ){
                           Text(
//                               text = "How was the quality of your call?",
                               text = stringResource(R.string.how_was_the_qulaity_of_call),

                               fontSize = 14.sp,
                               color = Color.White.copy(alpha = 0.8f)
                           )
                           RatingStarsWithDialog()
                       }
                       Row(
                           modifier = Modifier.fillMaxWidth(),
                           horizontalArrangement = Arrangement.SpaceEvenly
                       ) {
//                           CircularButton(
//                               icon = R.drawable.baseline_call_end_24,
//                               text = "Return",
//                               backgroundColor = Color.Gray,
//                               onClick = {
//                                   loadInterstitialAd= true
//                               }
//                           )


                           CircularButton(
                               icon = R.drawable.baseline_call_end_24,
//                               text = "Return",
                               text = stringResource(R.string.return_is),

                               backgroundColor = Color.Gray,
                               onClick = {
                                   shouldShowAd = true

                               }
//                               onClick = {
//                                   if (interstitialAd != null) {
//                                       interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
//                                           override fun onAdDismissedFullScreenContent() {
//                                               println("Ad dismissed, resetting value...")
//                                               interstitialAd = null
//                                               loadInterstitialAd = true // load next ad
//                                               safeNavController.popBackStack()
//
//                                           }
//
//                                           override fun onAdFailedToShowFullScreenContent(adError: AdError) {
//                                               println("Ad failed to show: ${adError.message}")
//                                               interstitialAd = null
//                                           }
//                                       }
//                                       activity?.let {
//                                           interstitialAd?.show(it)
//                                       }
//                                   } else {
//                                       // Optional: show a message ad not ready
//                                       println("Ad not loaded yet")
//                                   }
//                               }
                           )



                           CircularButtonWithWave(
                               icon = R.drawable.videocall,
//                               text = "Call again",
                               text = stringResource(R.string.call_again),

                               backgroundColor = Color(0xFFFF9800),
                               onClick = {
                                   shouldShowAd = true

                               }
                           )
                       }
                   }
               }
           }
       }
   )
}

@Composable
fun RatingStars() {
    Row(horizontalArrangement = Arrangement.Center) {
        repeat(5) {
            Icon(
//                painter = painterResource(id = R.drawable.ic_star), // Replace with star icon
                imageVector = Icons.Outlined.Star,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}




@Composable
fun RatingStasrs(
    maxStars: Int = 5,
    initialRating: Int = 0,
    onRatingChanged: (Int) -> Unit
) {
    var rating by remember { mutableStateOf(initialRating) }

    Row(horizontalArrangement = Arrangement.Center) {
        repeat(maxStars) { index ->
            Icon(
                imageVector = if (index < rating) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = null,
                tint = if (index < rating) Color(0xFFFFD700) else Color.Gray, // Gold for filled, Gray for empty
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        rating = index + 1
                        onRatingChanged(rating)
                    }
            )
        }
    }
}







@Composable
fun CircularButton(icon: Int, text: String, backgroundColor: Color, onClick: () -> Unit) {

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .background(backgroundColor)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.White.copy(alpha = 0.8f)
        )
    }
}

@Composable
fun CircularButtonWithWave(
    icon: Int,
    text: String,
    backgroundColor: Color,
    onClick: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition()

    // Animated scale for the expanding wave effect
    val waveScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    // Animated alpha for the fading effect
    val waveAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        )
    )

    Box(contentAlignment = Alignment.Center) {
        // Circular wave animation (background pulse)
        Canvas(
            modifier = Modifier
                .size(80.dp) // Adjust size for the wave effect
                .graphicsLayer(scaleX = waveScale, scaleY = waveScale, alpha = waveAlpha)
        ) {
            drawCircle(color = Color.White.copy(alpha = 0.3f))
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(backgroundColor)
                    .clickable { onClick() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = text,
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun RatingStarsWithDialog() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedRating by remember { mutableStateOf(0) }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        Text(text = "Tap to Rate", fontSize = 18.sp, fontWeight = FontWeight.Bold)

        // Rating Stars
        Row(horizontalArrangement = Arrangement.Center) {
            repeat(5) { index ->
                Icon(
                    imageVector = if (index < selectedRating) Icons.Filled.Star else Icons.Outlined.Star,
                    contentDescription = null,
                    tint = if (index < selectedRating) Color(0xFFFFD700) else Color.Gray,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            selectedRating = index + 1
                            showDialog = true
                        }
                )
            }
        }

        // Show Custom Rate Us Dialog
        CustomRateUsDialog(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            onSubmit = { rating ->
                selectedRating = rating
                showDialog = false
                println("User rated: $rating stars")
            }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCallEndedScreen() {
    CallEndedScreen(
        navController = rememberNavController(),
        profileImage = "drawable://fake1",
        onReturn = {}
    ) {}
}




fun loadAd(context: Context, onAdLoaded: (InterstitialAd) -> Unit) {
    InterstitialAd.load(
        context,
        "ca-app-pub-3940256099942544/1033173712", // test ad unit
        AdRequest.Builder().build(),
        object : InterstitialAdLoadCallback() {
            override fun onAdLoaded(ad: InterstitialAd) {
                onAdLoaded(ad)
            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                println("Ad failed: ${error.message}")
            }
        }
    )
}

