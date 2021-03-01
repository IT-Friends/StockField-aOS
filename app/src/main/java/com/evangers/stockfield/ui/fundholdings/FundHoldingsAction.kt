package com.evangers.stockfield.ui.fundholdings

import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.model.FundHoldingComparisonModel
import com.evangers.stockfield.domain.model.FundModel

sealed class FundHoldingsAction {
    class UpdateFund(val fundHoldings: List<FundHoldingComparisonModel>) : FundHoldingsAction()
    class ShowToast(val text: String) : FundHoldingsAction()
}