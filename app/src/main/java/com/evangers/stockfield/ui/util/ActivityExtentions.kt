package com.evangers.stockfield.ui.util

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.lifecycle.LifecycleOwner

fun ComponentActivity.onBackPressedDispatcher(
    lifecycleOwner: LifecycleOwner = this,
    callback: OnBackPressedCallback.() -> Unit
) {
    onBackPressedDispatcher.addCallback(owner = lifecycleOwner, onBackPressed = callback)
}