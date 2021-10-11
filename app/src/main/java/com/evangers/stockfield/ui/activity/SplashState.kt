package com.evangers.stockfield.ui.activity

import com.evangers.stockfield.ui.base.Event

interface SplashStateBind {
    var navToMainActivity: Event<Unit>?
    var showAlertDialog: Event<String>?
    var showUnknownErrorAlertDialog: Event<Unit>?
}

class SplashState(
    override var navToMainActivity: Event<Unit>? = null,
    override var showAlertDialog: Event<String>? = null,
    override var showUnknownErrorAlertDialog: Event<Unit>? = null
) : SplashStateBind {
    fun update(action: SplashAction) {
        when (action) {
            is SplashAction.NavToMainActivity -> {
                navToMainActivity = Event(Unit)
            }
            is SplashAction.ShowAlertDialog -> {
                showAlertDialog = Event(action.msg)
            }
            is SplashAction.ShowUnknownErrorAlertDialog -> {
                showUnknownErrorAlertDialog = Event(Unit)
            }
        }
    }
}
