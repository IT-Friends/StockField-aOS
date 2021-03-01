package com.evangers.stockfield.ui.fundholdings

import com.evangers.stockfield.domain.model.FundHoldingComparisonModel
import com.evangers.stockfield.ui.base.Event

interface FundHoldingsStateBind {
    var fundHoldings: Event<List<FundHoldingComparisonModel>>?
    var toastMessage: Event<String>?
}

class FundHoldingsState(
    override var fundHoldings: Event<List<FundHoldingComparisonModel>>? = null,
    override var toastMessage: Event<String>? = null
) : FundHoldingsStateBind {

    private val fundList = mutableListOf<FundHoldingComparisonModel>()

    fun update(action: FundHoldingsAction) {
        when (action) {
            is FundHoldingsAction.UpdateFund -> {
                fundList.addAll(action.fundHoldings)
                fundHoldings = Event(fundList)
            }
            is FundHoldingsAction.ShowToast -> {
                toastMessage = Event(action.text)
            }
        }
    }
}