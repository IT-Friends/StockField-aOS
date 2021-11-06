package com.evangers.stockfield.ui.setting.presenter

import android.view.View
import com.evangers.stockfield.ui.setting.SettingsItem
import javax.inject.Inject

class PresenterImpl @Inject constructor(
    private val openSourcePresenter: OpenSourcePresenter,
    private val versionPresenter: VersionPresenter,
    private val calculatorPresenter: CalculatorPresenter
) : Presenter {
    override fun initUi(item: SettingsItem.OpenSource, view: View) {
        openSourcePresenter.initUi(item, view)
    }

    override fun initUi(item: SettingsItem.Version, view: View) {
        versionPresenter.initUi(item, view)
    }

    override fun initUi(item: SettingsItem.Calculator, view: View) {
        calculatorPresenter.initUi(item, view)
    }
    
}