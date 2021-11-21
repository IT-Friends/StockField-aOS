package com.evangers.stockfield.ui.webview

import com.evangers.stockfield.ui.base.Event

interface WebViewStateBind {
    var navToBack: Event<Unit>?
}

class WebViewState(
    override var navToBack: Event<Unit>? = null,
) : WebViewStateBind {

    fun update(action: WebViewAction) {
        when (action) {
            is WebViewAction.NavToBack -> {
                navToBack = Event(Unit)
            }
        }
    }
}
