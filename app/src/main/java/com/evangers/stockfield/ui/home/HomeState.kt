package com.evangers.stockfield.ui.home

import androidx.fragment.app.Fragment
import com.evangers.stockfield.domain.model.CompanyModel
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.ui.base.Event
import com.evangers.stockfield.ui.fundholdings.FundHoldingsFragment

interface HomeStateBind {
    var companyList: Event<List<CompanyModel>>?
    var companyFundList: List<FundModel>?
    var fragmentList: List<Fragment>
    var toastMessage: Event<String>?
    var dateText: String?
    var isLoading: Event<Boolean>?
    var navToDetail: Event<Pair<String, String>>?
    var navToMore: Event<Unit>?
    var currentSpinnerPosition: Int
    var currentFundTabPosition: Int
    val dateMap: Map<Int, String>
    var displayExitDialog: Boolean
    var exitApp: Event<Unit>?
}

class HomeState(
    override var companyList: Event<List<CompanyModel>>? = null,
    override var companyFundList: List<FundModel>? = null,
    override var fragmentList: List<Fragment> = emptyList(),
    override var toastMessage: Event<String>? = null,
    override var dateText: String? = null,
    override var isLoading: Event<Boolean>? = null,
    override var navToDetail: Event<Pair<String, String>>? = null,
    override var navToMore: Event<Unit>? = null,
    override var currentSpinnerPosition: Int = -1,
    override var currentFundTabPosition: Int = 0,
    override var displayExitDialog: Boolean = false,
    override var exitApp: Event<Unit>? = null
) : HomeStateBind {

    override val dateMap = mutableMapOf<Int, String>()

    var hasLoaded = false

    fun update(action: HomeAction) {
        when (action) {
            is HomeAction.UpdateCompany -> {
                hasLoaded = true
                companyList = Event(action.companyList)
            }
            is HomeAction.UpdateCompanyFund -> {
                companyFundList = action.fundList
                fragmentList = action.fundList.mapIndexed { index, it ->
                    FundHoldingsFragment.newInstance(
                        action.controller,
                        it.name,
                        index
                    )
                }
            }
            is HomeAction.ShowToast -> {
                toastMessage = Event(action.text)
            }
            is HomeAction.UpdateDate -> {
                dateMap[action.pair.first] = action.pair.second
            }
            is HomeAction.UpdateLoadingState -> {
                isLoading = Event(action.isLoading)
            }
            is HomeAction.NavToDetail -> {
                navToDetail = Event(action.ticker to action.displayName)
            }
            is HomeAction.DisplayDate -> {
                dateText = dateMap[action.tabIndex]
            }
            is HomeAction.DisplayExitDialog -> {
                displayExitDialog = action.show
            }
            is HomeAction.ExitApp -> {
                exitApp = Event(Unit)
            }
            is HomeAction.NavToMore -> {
                navToMore = Event(Unit)
            }
        }
    }
}