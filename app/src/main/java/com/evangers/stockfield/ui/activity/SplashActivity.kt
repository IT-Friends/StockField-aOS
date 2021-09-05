package com.evangers.stockfield.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.evangers.stockfield.ui.util.AppOpenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    
    @Inject
    lateinit var appOpenManager: AppOpenManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appOpenManager.init()
        appOpenManager.fetchAd(
            onLoaded = {
                launchMainActivity()
            },
            onLoadFailed = {
                launchMainActivity()
            }
        )
    }

    private fun launchMainActivity() {
        val intent = Intent(this, MainActivity::class.java).apply {
            data = intent.data
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.extras
                .takeIf { it != null }
                ?.also { putExtras(it) }
        }
        startActivity(intent)
        finish()
    }
}