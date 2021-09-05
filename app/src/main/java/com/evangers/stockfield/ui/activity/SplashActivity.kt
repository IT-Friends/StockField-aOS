package com.evangers.stockfield.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchMainActivity()
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