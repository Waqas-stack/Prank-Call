package com.o9tech.prankcall
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import com.o9tech.prankcall.AppNavigation.Navigation
import com.o9tech.prankcall.ui.theme.PrankCallTheme
import com.o9tech.prankcall.utils.LocaleManager
import dagger.hilt.android.AndroidEntryPoint
import java.util.Arrays


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(LocaleManager.setLocale(base!!, LocaleManager.getSavedLanguage(base)))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        MobileAds.initialize(this)
        RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("F635E38E5056FB81EA1A0F064AE3A68F"))

        setContent {
            PrankCallTheme {
                Navigation()
            }
        }
    }
}
