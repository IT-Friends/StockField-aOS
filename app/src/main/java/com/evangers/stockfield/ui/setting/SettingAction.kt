package com.evangers.stockfield.ui.setting

sealed class SettingAction {
    object NavToBack : SettingAction()

    class SettingsUpdated(
        val calculator: SettingsItem.Calculator,
        val version: SettingsItem.Version,
        val openSource: SettingsItem.OpenSource
    ) : SettingAction()
}
