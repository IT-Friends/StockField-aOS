package com.evangers.stockfield.ui.home

import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.model.FundModel

sealed class HomeAction {
    class UpdateCompany(val companyList: List<CompanyModel>) : HomeAction()
    class UpdateCompanyFund(val fundList: List<String>) : HomeAction()
    class UpdateFund(val fund: FundModel) : HomeAction()
    class ShowToast(val text: String) : HomeAction()
}