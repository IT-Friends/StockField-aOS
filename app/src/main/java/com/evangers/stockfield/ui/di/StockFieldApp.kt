package com.evangers.stockfield.ui.di

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.evangers.stockfield.domain.usecase.SetInitOpenTime
import com.google.android.gms.ads.MobileAds
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
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
        FirebaseCrashlytics.getInstance().recordException(Throwable("app-1"))
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        FirebaseCrashlytics.getInstance().recordException(Throwable("app-2"))
        FirebaseApp.initializeApp(this)
        FirebaseCrashlytics.getInstance().recordException(Throwable("app-3"))
        MobileAds.initialize(this) {}
        FirebaseCrashlytics.getInstance().recordException(Throwable("app-4"))
        CoroutineScope(Dispatchers.Default).launch {
            FirebaseCrashlytics.getInstance().recordException(Throwable("app-5"))
            setInitOpenTime(System.currentTimeMillis())
            FirebaseCrashlytics.getInstance().recordException(Throwable("app-6"))
        }
    }
}