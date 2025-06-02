package com.o9tech.prankcall.Screen

import android.app.Application
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.RequestConfiguration
import dagger.hilt.android.HiltAndroidApp
import java.util.Arrays

@HiltAndroidApp
class myapp: Application() {
    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this)
        RequestConfiguration.Builder().setTestDeviceIds(Arrays.asList("F635E38E5056FB81EA1A0F064AE3A68F"))
    }
}