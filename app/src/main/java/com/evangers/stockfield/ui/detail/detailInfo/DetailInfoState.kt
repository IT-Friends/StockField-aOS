package com.evangers.stockfield.ui.detail.detailInfo

import com.evangers.stockfield.domain.model.StockModel
import com.evangers.stockfield.ui.base.Event

interface DetailInfoStateBind {
    var isLoading: Event<Boolean>?
    var stockInfo: Event<StockModel>?
}

class DetailInfoState(
    override var isLoading: Event<Boolean>? = null,
    override var stockInfo: Event<StockModel>? = null
) : DetailInfoStateBind {

    fun update(action: DetailInfoAction) {
        when (action) {
            is DetailInfoAction.UpdateDetail -> {
                stockInfo = Event(action.stockModel)
            }
            is DetailInfoAction.UpdateLoadingState -> {
                isLoading = Event(action.isLoading)
            }
        }
    }
}