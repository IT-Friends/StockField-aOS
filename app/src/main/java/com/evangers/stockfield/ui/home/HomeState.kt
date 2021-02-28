package com.evangers.stockfield.ui.home

import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.model.FundHoldingsModel
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.ui.base.Event

interface HomeStateBind {
    var companyList: Event<List<CompanyModel>>?
    var companyFundList: Event<List<FundModel>>?
    var fundHoldings: Event<FundHoldingsModel>?
    var toastMessage: Event<String>?
}

class HomeState(
    override var companyList: Event<List<CompanyModel>>? = null,
    override var companyFundList: Event<List<FundModel>>? = null,
    override var fundHoldings: Event<FundHoldingsModel>? = null,
    override var toastMessage: Event<String>? = null
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
        }
    }
}