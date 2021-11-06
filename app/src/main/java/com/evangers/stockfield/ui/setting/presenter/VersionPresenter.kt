package com.evangers.stockfield.ui.setting.presenter

import android.view.View
import com.evangers.stockfield.ui.setting.SettingsItem

interface VersionPresenter {
    fun initUi(item: SettingsItem.Version, view: View)
}