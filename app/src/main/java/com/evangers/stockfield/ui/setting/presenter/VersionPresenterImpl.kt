package com.evangers.stockfield.ui.setting.presenter

import android.view.View
import com.evangers.stockfield.databinding.ItemSettingVersionBinding
import com.evangers.stockfield.ui.setting.SettingsItem
import javax.inject.Inject

class VersionPresenterImpl @Inject constructor(
) : VersionPresenter {
    override fun initUi(item: SettingsItem.Version, view: View) {
        with(ItemSettingVersionBinding.bind(view)) {
            content.text = item.versionText
        }
    }
}