package com.evangers.stockfield.ui.di

import android.app.Application
import com.google.android.gms.ads.MobileAds
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class StockFieldApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MobileAds.initialize(this) {}
    }
}