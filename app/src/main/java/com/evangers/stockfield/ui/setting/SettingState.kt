package com.evangers.stockfield.ui.setting

import com.evangers.stockfield.ui.base.Event

interface SettingStateBind {
    var navToBack: Event<Unit>?
}

class SettingState(
    override var navToBack: Event<Unit>? = null
) : SettingStateBind {

    fun update(action: SettingAction) {
        when (action) {
            is SettingAction.NavToBack -> {
                navToBack = Event(Unit)
            }
        }
    }
}
