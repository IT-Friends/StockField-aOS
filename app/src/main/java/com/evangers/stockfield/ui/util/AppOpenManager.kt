package com.evangers.stockfield.ui.util

import android.app.Activity
import android.app.Application
import com.evangers.stockfield.R
import com.evangers.stockfield.domain.usecase.HasUsedOverHoneymoon
import com.evangers.stockfield.ui.util.FirebaseRemote.Companion.honeymoon_period
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.appopen.AppOpenAd
import com.google.android.gms.ads.appopen.AppOpenAd.AppOpenAdLoadCallback
import com.google.firebase.remoteconfig.ktx.get
import java.util.*
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


class AppOpenManager @Inject constructor(
    private val stockFieldApp: Application,
    private val firebaseRemote: FirebaseRemote,
    private val hasUsedOverHoneymoon: HasUsedOverHoneymoon
) {

    private var appOpenAd: AppOpenAd? = null
    private lateinit var loadCallback: AppOpenAdLoadCallback

    private var isShowingAd = false
    private var loadTime = 0L
    private var hasAdBeenShown = false

    private val adRequest: AdRequest
        get() = AdRequest.Builder().build()

    private var isOpenAdAvailableUser = false

    suspend fun init() {
        hasAdBeenShown = false
        val honeyMoonDay = firebaseRemote.config[honeymoon_period].asLong()
        debugLog("honeyMoonDay : $honeyMoonDay")
        val result = hasUsedOverHoneymoon.invoke(honeyMoonDay)
        when (result) {
            is HasUsedOverHoneymoon.Response.Success -> {
                isOpenAdAvailableUser = true
            }
            is HasUsedOverHoneymoon.Response.Failure -> {
                isOpenAdAvailableUser = false
            }

        }
    }

    suspend fun fetchAd() = suspendCoroutine<Response> { ct ->
        debugLog("${this.javaClass.simpleName} fetchAd() hasBeenShown : $hasAdBeenShown, isShowingAd : $isShowingAd")
        if (isAdAvailable() || hasAdBeenShown) {
            ct.resume(Response.LoadFailure)
            return@suspendCoroutine
        }
        loadCallback = object : AppOpenAdLoadCallback() {
            override fun onAdLoaded(ad: AppOpenAd) {
                debugLog("${this@AppOpenManager.javaClass.simpleName} adLoaded")
                appOpenAd = ad
                loadTime = Date().time
                ct.resume(Response.LoadSuccess)
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                debugLog("${this.javaClass.simpleName} adFailed ${loadAdError.message}")
                ct.resume(Response.LoadFailure)
            }
        }

        AppOpenAd.load(
            stockFieldApp, stockFieldApp.getString(R.string.admob_open_unit_id), adRequest,
            AppOpenAd.APP_OPEN_AD_ORIENTATION_PORTRAIT, loadCallback
        )
    }

    private fun wasLoadTimeLessThanNHoursAgo(hours: Long): Boolean {
        val dateDifference = Date().time - loadTime
        val numMilliSecondsPerHour = 3600000L
        return dateDifference < numMilliSecondsPerHour * hours
    }

    private fun isAdAvailable(): Boolean {
        return isOpenAdAvailableUser && appOpenAd != null && wasLoadTimeLessThanNHoursAgo(4)
    }

    fun showAdIfAvailable(activity: Activity) {
        debugLog("${this.javaClass.simpleName} showAdIfAvailable() hasBeenShown : $hasAdBeenShown, isShowingAd : $isShowingAd")
        if (hasAdBeenShown) {
            return
        }
        if (!isShowingAd && isAdAvailable()) {
            val fullScreenContentCallback: FullScreenContentCallback =
                object : FullScreenContentCallback() {
                    override fun onAdDismissedFullScreenContent() {
                        appOpenAd = null
                        isShowingAd = false
                    }

                    override fun onAdFailedToShowFullScreenContent(adError: AdError) {}
                    override fun onAdShowedFullScreenContent() {
                        isShowingAd = true
                        hasAdBeenShown = true
                    }
                }
            appOpenAd?.fullScreenContentCallback = fullScreenContentCallback
            appOpenAd?.show(activity)
        }
    }

    sealed class Response {
        object LoadSuccess : Response()
        object LoadFailure : Response()
    }

}