package com.evangers.stockfield.ui.di

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.evangers.stockfield.domain.usecase.SetInitOpenTime
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class StockFieldApp : Application() {

    @Inject
    lateinit var setInitOpenTime: SetInitOpenTime

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        FirebaseApp.initializeApp(this)
        MobileAds.initialize(this) {}
        CoroutineScope(Dispatchers.Default).launch {
            setInitOpenTime(System.currentTimeMillis())
        }
    }
}