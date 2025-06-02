package com.o9tech.prankcall.Add.Interstitialadd

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback

//var interstitialAd: InterstitialAd? by remember { mutableStateOf(null) }
//
//LaunchedEffect(loadInterstitialAd) {
//    if(loadInterstitialAd){
//        InterstitialAd.load(
//            context,
//            AppConstants.Interstitial_AD_UNIT_ID,
////            "ca-app-pub-3940256099942544/1033173712",
//            AdRequest.Builder().build(),
//            object : InterstitialAdLoadCallback() {
//                override fun onAdFailedToLoad(error: LoadAdError) {
//                    println("Ad failed to load: ${error.message}")
//
//                }
//
//                override fun onAdLoaded(loadedAd: InterstitialAd) {
//                    println("Ad Loaded Successfully")
//                    interstitialAd = loadedAd
//                }
//            }
//        )
//    }
//}


//LaunchedEffect(bpmValue) {
//    if (bpmValue > 0) {
//        loadInterstitialAd = false
//        isInternetAvailable.value = checkInternet(context)
//        if (isInternetAvailable.value && interstitialAd != null) {
//            interstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
//                override fun onAdDismissedFullScreenContent() {
//                    super.onAdDismissedFullScreenContent()
//                    println("Ad dismissed, resetting value...")
////                        bpmValue = 0 // Reset BPM after ad is dismissed
//                    interstitialAd = null // Clear ad reference
//                    oldvalue = bpmValue
//                    loadInterstitialAd=true
//                }
//
//                override fun onAdFailedToShowFullScreenContent(adError: AdError) {
//                    println("Ad failed to show: ${adError.message}")
//                    interstitialAd = null
//                }
//            }
//
//            // 🔥 Show the Ad
////                interstitialAd?.show(co)
//            activity?.let {
//                interstitialAd?.show(it)
//            }
//        }
//    }
//}