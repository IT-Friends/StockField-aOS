package com.evangers.stockfield.ui.util

import com.evangers.stockfield.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

class FirebaseRemote {

    companion object {
        val honeymoon_period = "honeymoon_period"
    }

    private val DEFAULT = mapOf<String, Any>(
        honeymoon_period to 7
    )

    val config = getFirebaseRemoteConfig()

    private fun getFirebaseRemoteConfig(): FirebaseRemoteConfig {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) {
                0
            } else {
                60 * 60
            }
        }
        remoteConfig.apply {
            setConfigSettingsAsync(configSettings)
            setDefaultsAsync(DEFAULT)
            fetchAndActivate().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val updated = task.result
                    debugLog("Config params updated: $updated")
                } else {
                    debugLog("config failed")
                }
            }
        }
        return remoteConfig
    }

}