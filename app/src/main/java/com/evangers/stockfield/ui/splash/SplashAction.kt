package com.evangers.stockfield.ui.splash

sealed class SplashAction {
    object NavToMainActivity : SplashAction()
    class ShowAlertDialog(val msg: String) : SplashAction()
    object ShowUnknownErrorAlertDialog : SplashAction()
    object ShowServerCheckingMessage : SplashAction()
    object ShowDataCheckingMessage : SplashAction()
}
