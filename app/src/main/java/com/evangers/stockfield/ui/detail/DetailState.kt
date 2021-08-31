package com.evangers.stockfield.ui.detail

import com.evangers.stockfield.ui.base.Event

interface DetailStateBind {
    var toastMessage: Event<String>?
    var isLoading: Event<Boolean>?
    var updateTitle: Event<Pair<String, String>>?
    var navToBack: Event<Unit>?
    var navToFundDetail: Event<Pair<String, String>>?
}

class DetailState(
    override var toastMessage: Event<String>? = null,
    override var isLoading: Event<Boolean>? = null,
    override var updateTitle: Event<Pair<String, String>>? = null,
    override var navToBack: Event<Unit>? = null,
    override var navToFundDetail: Event<Pair<String, String>>? = null
) : DetailStateBind {

    fun update(action: DetailAction) {
        when (action) {
            is DetailAction.ShowToast -> {
                toastMessage = Event(action.text)
            }
            is DetailAction.UpdateLoadingState -> {
                isLoading = Event(action.isLoading)
            }
            is DetailAction.UpdateTitle -> {
                updateTitle = Event(action.titleAndSub)
            }
            is DetailAction.NavToBack -> {
                navToBack = Event(Unit)
            }
            is DetailAction.NavToFundDetail -> {
                navToFundDetail = Event(action.ticker to action.fundName)
            }
        }
    }
}