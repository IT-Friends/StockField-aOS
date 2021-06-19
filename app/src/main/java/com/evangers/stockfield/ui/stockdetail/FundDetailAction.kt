package com.evangers.stockfield.ui.stockdetail

import com.evangers.stockfield.domain.model.HistoryModel

sealed class FundDetailAction {
    class ShowToast(val text: String) : FundDetailAction()
    class UpdateLoadingState(val isLoading: Boolean) : FundDetailAction()
    class UpdateTitle(val tickerAndFund: Pair<String, String>) : FundDetailAction()
    class UpdateFundHistory(val total: Int, val list: List<HistoryModel>) : FundDetailAction()
    object NavToBack : FundDetailAction()
}