package com.evangers.stockfield.ui.setting

import com.evangers.stockfield.ui.base.Event

interface SettingStateBind {
    var navToBack: Event<Unit>?
    var navToCalculator: Event<Unit>?
    var navToOpenSource: Event<Unit>?
    val items: MutableList<SettingsItem>
}

class SettingState(
    override var navToBack: Event<Unit>? = null,
    override var navToCalculator: Event<Unit>? = null,
    override var navToOpenSource: Event<Unit>? = null,
    override val items: MutableList<SettingsItem> = mutableListOf()
) : SettingStateBind {

    fun update(action: SettingAction) {
        when (action) {
            is SettingAction.NavToBack -> {
                navToBack = Event(Unit)
            }
            is SettingAction.SettingsUpdated -> {
                items.clear()
                items.add(action.calculator)
                items.add(action.version)
                items.add(action.openSource)
            }
            is SettingAction.NavToOpenSource -> {
                navToOpenSource = Event(Unit)
            }
            is SettingAction.NavToCalculator -> {
                navToCalculator = Event(Unit)
            }
        }
    }
}