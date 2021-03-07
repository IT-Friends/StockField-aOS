package com.evangers.stockfield.ui.home

import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.model.FundHoldingComparisonModel
import com.evangers.stockfield.domain.model.FundModel

sealed class HomeAction {
    class UpdateCompany(val companyList: List<CompanyModel>) : HomeAction()
    class UpdateCompanyFund(val fundList: List<FundModel>) : HomeAction()
    class UpdateFund(val fundHoldings: List<FundHoldingComparisonModel>) : HomeAction()
    class ShowToast(val text: String) : HomeAction()
    class UpdateLoadingState(val isLoading: Boolean) : HomeAction()
}