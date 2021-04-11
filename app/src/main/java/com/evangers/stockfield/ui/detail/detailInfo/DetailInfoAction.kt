package com.evangers.stockfield.ui.detail.detailInfo

import com.evangers.stockfield.domain.model.StockModel
import com.github.mikephil.charting.data.LineData

sealed class DetailInfoAction {
    class UpdateDetail(val stockModel: StockModel) : DetailInfoAction()
    class UpdateLoadingState(val isLoading: Boolean) : DetailInfoAction()
    class UpdateChartLoadingState(val isLoading: Boolean) : DetailInfoAction()
    class UpdateHistoryData(val page: Int, val lineData: LineData) : DetailInfoAction()
}