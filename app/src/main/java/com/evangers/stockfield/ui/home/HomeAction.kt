package com.evangers.stockfield.ui.home

import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.ui.fundholdings.HomeController

sealed class HomeAction {
    class UpdateCompany(val companyList: List<CompanyModel>) : HomeAction()
    class UpdateCompanyFund(
        val controller: HomeController,
        val fundList: List<FundModel>
    ) : HomeAction()

    class ShowToast(val text: String) : HomeAction()
    class UpdateDate(val pair: Pair<Int, String>) : HomeAction()
    class UpdateLoadingState(val isLoading: Boolean) : HomeAction()
    class NavToDetail(val ticker: String, val displayName: String) : HomeAction()
    class DisplayDate(val tabIndex: Int) : HomeAction()
    class DisplayExitDialog(val show: Boolean) : HomeAction()
    object ExitApp : HomeAction()
    object NavToMore : HomeAction()
}