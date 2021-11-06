package com.evangers.stockfield.ui.setting

import android.view.View
import com.evangers.stockfield.R
import com.evangers.stockfield.ui.setting.presenter.Presentable
import com.evangers.stockfield.ui.setting.presenter.Presenter

sealed class SettingsItem(
    open val id: Int,
    open val layoutId: Int
) : Presentable {

    enum class SettingOrder {
        Calculator,
        OpenSource,
        Version;
    }

    data class Calculator(
        override val id: Int = SettingOrder.Calculator.ordinal,
        override val layoutId: Int = R.layout.item_setting_calculator
    ) : SettingsItem(id = id, layoutId = layoutId) {
        override fun presentUi(presenter: Presenter, view: View) {
            presenter.initUi(this, view)
        }
    }

    data class Version(
        override val id: Int = SettingOrder.Version.ordinal,
        override val layoutId: Int = R.layout.item_setting_version,
        val versionText: String
    ) : SettingsItem(id = id, layoutId = layoutId) {
        override fun presentUi(presenter: Presenter, view: View) {
            presenter.initUi(this, view)
        }
    }

    data class OpenSource(
        override val id: Int = SettingOrder.OpenSource.ordinal,
        override val layoutId: Int = R.layout.item_setting_opensource
    ) : SettingsItem(id = id, layoutId = layoutId) {
        override fun presentUi(presenter: Presenter, view: View) {
            presenter.initUi(this, view)
        }
    }

}