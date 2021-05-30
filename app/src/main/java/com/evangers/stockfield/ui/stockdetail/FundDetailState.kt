package com.evangers.stockfield.ui.stockdetail

import com.evangers.stockfield.ui.base.Event

interface FundDetailStateBind {
    var toastMessage: Event<String>?
    var isLoading: Event<Boolean>?
    var updateTitle: Event<Pair<String, String>>?
    var navToBack: Event<Unit>?
}

class FundDetailState(
    override var toastMessage: Event<String>? = null,
    override var isLoading: Event<Boolean>? = null,
    override var updateTitle: Event<Pair<String, String>>? = null,
    override var navToBack: Event<Unit>? = null
) : FundDetailStateBind {

    fun update(actionFund: FundDetailAction) {
        when (actionFund) {
            is FundDetailAction.ShowToast -> {
                toastMessage = Event(actionFund.text)
            }
            is FundDetailAction.UpdateLoadingState -> {
                isLoading = Event(actionFund.isLoading)
            }
            is FundDetailAction.UpdateTitle -> {
                updateTitle = Event(actionFund.titleAndSub)
            }
            is FundDetailAction.NavToBack -> {
                navToBack = Event(Unit)
            }

        }
    }
}