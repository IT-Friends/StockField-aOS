package com.evangers.stockfield.ui.util

import com.evangers.stockfield.BuildConfig

inline fun isProductionFlavor() = BuildConfig.FLAVOR.startsWith("production")