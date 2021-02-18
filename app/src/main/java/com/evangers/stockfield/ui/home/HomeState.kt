package com.evangers.stockfield.ui.home

import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.ui.base.Event

class HomeState(
    override var fund: Event<FundModel>? = null,
    override var toastMessage: Event<String>? = null
) : HomeStateBind {

    fun update(action: HomeAction) {
        when (action) {
            is HomeAction.UpdateFund -> {
                fund = Event(action.fund)
            }
            is HomeAction.ShowToast -> {
                toastMessage = Event(action.text)
            }
        }
    }
}

interface HomeStateBind {
    var fund: Event<FundModel>?
    var toastMessage: Event<String>?
}