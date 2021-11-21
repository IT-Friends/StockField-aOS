package com.evangers.stockfield.ui.setting.presenter

import android.view.View
import com.evangers.stockfield.databinding.ItemSettingOpensourceBinding
import com.evangers.stockfield.ui.setting.SettingsController
import com.evangers.stockfield.ui.setting.SettingsItem
import javax.inject.Inject

class OpenSourcePresenterImpl @Inject constructor(
    private val settingsController: SettingsController
) : OpenSourcePresenter {
    override fun initUi(item: SettingsItem.OpenSource, view: View) {
        with(ItemSettingOpensourceBinding.bind(view)) {
            this.root.setOnClickListener { settingsController.onOpenSourceLicenseClicked() }
        }
    }
}