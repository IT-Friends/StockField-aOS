package com.evangers.stockfield.ui.stockdetail

import android.graphics.Color
import com.evangers.stockfield.domain.model.HistoryModel
import com.evangers.stockfield.ui.base.Event
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

interface FundDetailStateBind {
    var toastMessage: Event<String>?
    var isLoading: Event<Boolean>?
    var updateTitle: Event<Pair<String, String>>?
    var fundHistoryList: Event<List<HistoryModel>>?
    var navToBack: Event<Unit>?
    var lineChartList: Event<LineData>?
}

class FundDetailState(
    override var toastMessage: Event<String>? = null,
    override var isLoading: Event<Boolean>? = null,
    override var fundHistoryList: Event<List<HistoryModel>>? = null,
    override var updateTitle: Event<Pair<String, String>>? = null,
    override var navToBack: Event<Unit>? = null,
    override var lineChartList: Event<LineData>? = null
) : FundDetailStateBind {

    private var ticker: String = ""
    private var fundName: String = ""
    var fundHistoryTotalSize = 0

    fun update(action: FundDetailAction) {
        when (action) {
            is FundDetailAction.ShowToast -> {
                toastMessage = Event(action.text)
            }
            is FundDetailAction.UpdateLoadingState -> {
                isLoading = Event(action.isLoading)
            }
            is FundDetailAction.UpdateTitle -> {
                ticker = action.tickerAndFund.first
                fundName = action.tickerAndFund.second
                updateTitle = Event(action.tickerAndFund)
            }
            is FundDetailAction.NavToBack -> {
                navToBack = Event(Unit)
            }
            is FundDetailAction.UpdateFundHistory -> {
                fundHistoryTotalSize = action.total
                fundHistoryList = Event(action.list)


                val dataSet = LineDataSet(
                    action.list.reversed().mapIndexed { index, historyModel ->
                        Entry(
                            index.toFloat(),
                            historyModel.shares.toFloat()
                        )
                    },
                    "shares"
                ).apply {
                    highLightColor = Color.TRANSPARENT

                }
                lineChartList = Event(LineData(dataSet))
            }
        }
    }
}