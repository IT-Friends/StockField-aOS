package com.evangers.stockfield.ui.base

import android.content.Context
import android.widget.Toast
import androidx.navigation.ActivityNavigator
import com.evangers.stockfield.R

class NavRootControllerImpl : NavRootController {
    private val backButtonTimeout = 1500L
    private var lastBackPressedTime = 0L
    override fun onGlobalBackPressed(context: Context) {
        val duration = System.currentTimeMillis() - lastBackPressedTime

        if (duration < backButtonTimeout) {
            ActivityNavigator(context).popBackStack()
        } else {
            Toast.makeText(context, R.string.backButtonForExit, Toast.LENGTH_SHORT).show()
            lastBackPressedTime = System.currentTimeMillis()
        }
    }
}