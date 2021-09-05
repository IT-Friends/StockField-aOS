package com.evangers.stockfield.ui.util

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.DialogExitBinding
import com.google.android.ads.nativetemplates.NativeTemplateStyle
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest


class ExitDialog(
    context: Context,
    private val listener: PopupDialogListener? = null
) : AlertDialog(context, false, null) {

    init {
        initUi()
        initDialog()
    }

    private lateinit var binding: DialogExitBinding

    private fun initUi() {
        with(View.inflate(context, R.layout.dialog_exit, null)) {
            setView(this, 0, 0, 0, 0)
            binding = DialogExitBinding.bind(this)

            val adLoader: AdLoader =
                AdLoader.Builder(context, context.getString(R.string.admob_close_unit_id))
                    .forNativeAd { nativeAd ->
                        val styles = NativeTemplateStyle.Builder()
                            .build()

                        binding.nativeAd.setStyles(styles)
                        binding.nativeAd.setNativeAd(nativeAd)
                    }
                    .build()

            adLoader.loadAd(AdRequest.Builder().build())

            binding.returnToAppButton.setOnClickListener {
                listener?.onReturnToAppClicked()
            }

            binding.exitAppButton.setOnClickListener {
                listener?.onExitButtonClicked()
            }
            setOnCancelListener { listener?.onReturnToAppClicked() }
        }
    }

    override fun onBackPressed() {
        listener?.onExitButtonClicked()
    }

    private fun initDialog() {
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        setCanceledOnTouchOutside(true)
    }
}

interface PopupDialogListener {
    fun onExitButtonClicked()

    fun onReturnToAppClicked()
}