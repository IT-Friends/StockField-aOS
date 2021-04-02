package com.evangers.stockfield.ui.detail.detailInfo

import com.evangers.stockfield.domain.model.StockModel

sealed class DetailInfoAction {
    class UpdateDetail(val stockModel: StockModel) : DetailInfoAction()
    class UpdateLoadingState(val isLoading: Boolean) : DetailInfoAction()
}