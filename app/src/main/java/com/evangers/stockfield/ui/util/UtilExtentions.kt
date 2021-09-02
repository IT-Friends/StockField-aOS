package com.evangers.stockfield.ui.util

import android.util.Log
import com.evangers.stockfield.BuildConfig

fun debugLog(text: Any) {
    if(BuildConfig.DEBUG) {
        Log.d("[StockField]", text.toString())
    }
}

fun Float?.toText(): String {
    return (this ?: "정보없음").toString()
}

fun Double?.toText(): String {
    return (this ?: "정보없음").toString()
}