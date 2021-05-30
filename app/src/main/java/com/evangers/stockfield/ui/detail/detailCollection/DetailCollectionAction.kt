package com.evangers.stockfield.ui.detail.detailCollection

import com.evangers.stockfield.domain.model.FundModel

sealed class DetailCollectionAction {
    class UpdateLoadingState(val isLoading: Boolean) : DetailCollectionAction()
    class UpdateFundList(val total: Int, val list: List<FundModel>) : DetailCollectionAction()
    class ShowToast(val msg: Int) : DetailCollectionAction()
    class NavToFundDetail(val fundName: String) : DetailCollectionAction()
}