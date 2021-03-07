package com.evangers.stockfield.ui.fundholdings

import com.evangers.stockfield.domain.model.FundHoldingComparisonModel
import com.evangers.stockfield.ui.base.Event

interface FundHoldingsStateBind {
    var fundHoldings: Event<List<FundHoldingComparisonModel>>?
    var toastMessage: Event<String>?
    var isLoading: Event<Boolean>?
}

class FundHoldingsState(
    override var fundHoldings: Event<List<FundHoldingComparisonModel>>? = null,
    override var toastMessage: Event<String>? = null,
    override var isLoading: Event<Boolean>? = null
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
            is FundHoldingsAction.UpdateLoadingState->{
                isLoading = Event(action.isLoading)
            }
        }
    }
}