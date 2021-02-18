package com.evangers.stockfield.ui.home

import com.evangers.stockfield.domain.model.FundModel

sealed class HomeAction {
    class UpdateFund(val fund: FundModel) : HomeAction()
    class ShowToast(val text: String) : HomeAction()
}