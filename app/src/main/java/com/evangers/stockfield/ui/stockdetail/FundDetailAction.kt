package com.evangers.stockfield.ui.stockdetail

sealed class FundDetailAction {
    class ShowToast(val text: String) : FundDetailAction()
    class UpdateLoadingState(val isLoading: Boolean) : FundDetailAction()
    class UpdateTitle(val titleAndSub: Pair<String, String>) : FundDetailAction()
    object NavToBack : FundDetailAction()
}