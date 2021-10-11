package com.evangers.stockfield.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.ActivitySplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModels()

    lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
            state.showServerLoadingMessage?.getValueIfNotHandled()?.let {
                var dot = ""
                for (i in 0..it) {
                    dot += ". "
                }
                val text = "${getString(R.string.checkingServerState)}$dot"
                binding.loadingStatus.text = text
            }
            state.showDataLoadingMessage?.getValueIfNotHandled()?.let {
                var dot = ""
                for (i in 0..it) {
                    dot += ". "
                }
                val text = "${getString(R.string.loadingData)}$dot"
                binding.loadingStatus.text = text
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