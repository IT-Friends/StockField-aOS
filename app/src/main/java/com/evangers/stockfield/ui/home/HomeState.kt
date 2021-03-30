package com.evangers.stockfield.ui.home

import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.model.FundHoldingComparisonModel
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.ui.base.Event

interface HomeStateBind {
    var companyList: Event<List<CompanyModel>>?
    var companyFundList: Event<List<FundModel>>?
    var fundHoldings: Event<List<FundHoldingComparisonModel>>?
    var toastMessage: Event<String>?
    var dateText: Event<String>?
    var isLoading: Event<Boolean>?
    var navToDetail: Event<Pair<String, String>>?
}

class HomeState(
    override var companyList: Event<List<CompanyModel>>? = null,
    override var companyFundList: Event<List<FundModel>>? = null,
    override var fundHoldings: Event<List<FundHoldingComparisonModel>>? = null,
    override var toastMessage: Event<String>? = null,
    override var dateText: Event<String>? = null,
    override var isLoading: Event<Boolean>? = null,
    override var navToDetail: Event<Pair<String, String>>? = null
) : HomeStateBind {

    fun update(action: HomeAction) {
        when (action) {
            is HomeAction.UpdateCompany -> {
                companyList = Event(action.companyList)
            }
            is HomeAction.UpdateCompanyFund -> {
                companyFundList = Event(action.fundList)
            }
            is HomeAction.UpdateFund -> {
                fundHoldings = Event(action.fundHoldings)
            }
            is HomeAction.ShowToast -> {
                toastMessage = Event(action.text)
            }
            is HomeAction.UpdateDate -> {
                dateText = Event(action.text)
            }
            is HomeAction.UpdateLoadingState -> {
                isLoading = Event(action.isLoading)
            }
            is HomeAction.NavToDetail -> {
                navToDetail = Event(action.ticker to action.displayName)
            }
        }
    }
}