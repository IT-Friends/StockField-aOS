package com.evangers.stockfield.ui.setting.presenter

import android.view.View
import com.evangers.stockfield.ui.setting.SettingsItem

interface OpenSourcePresenter {
    fun initUi(item: SettingsItem.OpenSource, view: View)
}