package com.evangers.stockfield.ui.detail.detailCollection

import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.ui.base.Event

interface DetailCollectionStateBind {
    var isLoading: Event<Boolean>?
    var toast: Event<Int>?
    var fundList: Event<List<FundModel>>?
    var fundTotalCount: Int
    var navToFundDetail: Event<Pair<String, String>>?
}

class DetailCollectionState(
    override var isLoading: Event<Boolean>? = null,
    override var toast: Event<Int>? = null,
    override var fundList: Event<List<FundModel>>? = null,
    override var fundTotalCount: Int = 0,
    override var navToFundDetail: Event<Pair<String, String>>? = null
) : DetailCollectionStateBind {

    private val fundSet = sortedSetOf<FundModel>({ p0, p1 -> p0.id - p1.id })
    var ticker = ""

    fun update(action: DetailCollectionAction) {
        when (action) {
            is DetailCollectionAction.UpdateLoadingState -> {
                isLoading = Event(action.isLoading)
            }
            is DetailCollectionAction.ShowToast -> {
                toast = Event(action.msg)
            }
            is DetailCollectionAction.UpdateFundList -> {
                fundTotalCount = action.total
                fundSet.addAll(action.list)
                fundList = Event(fundSet.toList())
            }
            is DetailCollectionAction.NavToFundDetail -> {
                navToFundDetail = Event(ticker to action.fundName)
            }
        }
    }
}