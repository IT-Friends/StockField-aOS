package com.evangers.stockfield.ui.util

import android.util.Log

fun debugLog(text: Any) {
    Log.d("[StockField]", text.toString())
}

fun Float?.toText(): String {
    return (this ?: "정보없음").toString()
}

fun Double?.toText(): String {
    return (this ?: "정보없음").toString()
}