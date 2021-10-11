package com.evangers.stockfield.ui.activity

import com.evangers.stockfield.ui.base.Event

interface SplashStateBind {
    var navToMainActivity: Event<Unit>?
    var showAlertDialog: Event<String>?
    var showUnknownErrorAlertDialog: Event<Unit>?
    var showServerLoadingMessage: Event<Int>?
    var showDataLoadingMessage: Event<Int>?
}

class SplashState(
    override var navToMainActivity: Event<Unit>? = null,
    override var showAlertDialog: Event<String>? = null,
    override var showUnknownErrorAlertDialog: Event<Unit>? = null,
    override var showServerLoadingMessage: Event<Int>? = null,
    override var showDataLoadingMessage: Event<Int>? = null
) : SplashStateBind {

    var checkingCount = 0

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
            is SplashAction.ShowServerCheckingMessage -> {
                showServerLoadingMessage = Event(checkingCount++ % 3)
            }
            is SplashAction.ShowDataCheckingMessage -> {
                showDataLoadingMessage = Event(checkingCount++ % 3)
            }
        }
    }
}
