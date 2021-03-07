package com.evangers.stockfield.ui.fundholdings

import com.evangers.stockfield.domain.model.FundHoldingComparisonModel

sealed class FundHoldingsAction {
    class UpdateFund(val fundHoldings: List<FundHoldingComparisonModel>) : FundHoldingsAction()
    class ShowToast(val text: String) : FundHoldingsAction()
    class UpdateLoadingState(val isLoading: Boolean) : FundHoldingsAction()
}