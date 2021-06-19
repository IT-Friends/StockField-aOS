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
    var navToBack: Event<Unit>?
    var lineChartList: Event<LineData>?
}

class FundDetailState(
    override var toastMessage: Event<String>? = null,
    override var isLoading: Event<Boolean>? = null,
    override var updateTitle: Event<Pair<String, String>>? = null,
    override var navToBack: Event<Unit>? = null,
    override var lineChartList: Event<LineData>? = null
) : FundDetailStateBind {

    fun update(actionFund: FundDetailAction) {
        when (actionFund) {
            is FundDetailAction.ShowToast -> {
                toastMessage = Event(actionFund.text)
            }
            is FundDetailAction.UpdateLoadingState -> {
                isLoading = Event(actionFund.isLoading)
            }
            is FundDetailAction.UpdateTitle -> {
                updateTitle = Event(actionFund.titleAndSub)
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