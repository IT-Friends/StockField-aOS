package com.evangers.stockfield.ui.detail.detailInfo

import com.evangers.stockfield.domain.model.StockModel
import com.evangers.stockfield.ui.base.Event
import com.github.mikephil.charting.data.LineData

interface DetailInfoStateBind {
    var isLoading: Event<Boolean>?
    var isChartLoading: Event<Boolean>?
    var stockInfo: Event<StockModel>?
    var historyData: Event<LineData>?
}

class DetailInfoState(
    override var isLoading: Event<Boolean>? = null,
    override var isChartLoading: Event<Boolean>? = null,
    override var stockInfo: Event<StockModel>? = null,
    override var historyData: Event<LineData>? = null
) : DetailInfoStateBind {

    fun update(action: DetailInfoAction) {
        when (action) {
            is DetailInfoAction.UpdateDetail -> {
                stockInfo = Event(action.stockModel)
            }
            is DetailInfoAction.UpdateLoadingState -> {
                isLoading = Event(action.isLoading)
            }
            is DetailInfoAction.UpdateChartLoadingState -> {
                isChartLoading = Event(action.isLoading)
            }
            is DetailInfoAction.UpdateHistoryData -> {
                historyData = Event(action.lineData)
            }
        }
    }
}