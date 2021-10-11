package com.evangers.stockfield.ui.base

import android.content.Context
import androidx.navigation.ActivityNavigator

class NavRootControllerImpl : NavRootController {
    override fun onGlobalBackPressed(context: Context) {
        ActivityNavigator(context).popBackStack()
    }
}