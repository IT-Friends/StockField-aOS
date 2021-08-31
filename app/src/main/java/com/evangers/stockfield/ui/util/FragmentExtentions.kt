package com.evangers.stockfield.ui.util

import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment

fun Fragment.showToast(msg: String) {
    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
}

fun Fragment.onBackPressedDispatcher(callback: OnBackPressedCallback.() -> Unit) {
    requireActivity().onBackPressedDispatcher(this, callback)
}
