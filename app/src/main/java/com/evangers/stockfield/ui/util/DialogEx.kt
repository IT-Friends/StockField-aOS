package com.evangers.stockfield.ui.util

import android.app.Dialog

inline var Dialog.isVisible: Boolean
    get() = isShowing
    set(value) {
        if (value) show() else dismiss()
    }