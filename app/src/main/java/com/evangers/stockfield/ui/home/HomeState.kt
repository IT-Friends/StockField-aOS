package com.evangers.stockfield.ui.home

import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.ui.base.Event

interface HomeStateBind {
    var companyList: Event<List<CompanyModel>>?
    var fund: Event<FundModel>?
    var toastMessage: Event<String>?
}

class HomeState(
    override var companyList: Event<List<CompanyModel>>? = null,
    override var fund: Event<FundModel>? = null,
    override var toastMessage: Event<String>? = null
) : HomeStateBind {

    fun update(action: HomeAction) {
        when (action) {
            is HomeAction.UpdateCompany -> {
                companyList = Event(action.companyList)
            }
            is HomeAction.UpdateFund -> {
                fund = Event(action.fund)
            }
            is HomeAction.ShowToast -> {
                toastMessage = Event(action.text)
            }
        }
    }
}