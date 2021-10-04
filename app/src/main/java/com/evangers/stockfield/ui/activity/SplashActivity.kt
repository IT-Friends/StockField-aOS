package com.evangers.stockfield.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.evangers.stockfield.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.start()
        initBinding()
    }

    private fun initBinding() {
        viewModel.liveData.observe(this, { state ->
            state.navToMainActivity?.getValueIfNotHandled()?.let {
                launchMainActivity()
            }
            state.showAlertDialog?.getValueIfNotHandled()?.let {
                alertView(it)
            }
            state.showUnknownErrorAlertDialog?.getValueIfNotHandled()?.let {
                alertView(getString(R.string.commonErrorMessage))
            }
        })
    }

    private fun launchMainActivity() {
        val intent = Intent(this@SplashActivity, MainActivity::class.java).apply {
            data = intent.data
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            intent.extras
                .takeIf { it != null }
                ?.also { putExtras(it) }
        }
        startActivity(intent)
        finish()
    }

    private fun alertView(message: String) {
        AlertDialog.Builder(this@SplashActivity)
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(R.string.confirm) { dialog, i ->
                this@SplashActivity.finish()
                dialog.dismiss()
            }.show()
    }

}