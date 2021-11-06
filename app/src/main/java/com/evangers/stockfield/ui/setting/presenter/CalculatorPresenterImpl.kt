package com.evangers.stockfield.ui.setting.presenter

import android.view.View
import com.evangers.stockfield.databinding.ItemSettingCalculatorBinding
import com.evangers.stockfield.ui.setting.SettingsController
import com.evangers.stockfield.ui.setting.SettingsItem
import javax.inject.Inject

class CalculatorPresenterImpl @Inject constructor(
    private val settingsController: SettingsController
) : CalculatorPresenter {
    override fun initUi(item: SettingsItem.Calculator, view: View) {
        with(ItemSettingCalculatorBinding.bind(view)) {
            stockCalculatorImageView.setOnClickListener { settingsController.onCalculatorClicked() }
        }
    }
}