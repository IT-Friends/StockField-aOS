package com.evangers.stockfield.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.domain.usecase.GetCompanies
import com.evangers.stockfield.domain.usecase.GetFundListFromCompany
import com.evangers.stockfield.ui.util.debugLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCompanies: GetCompanies,
    private val getFundListFromCompany: GetFundListFromCompany
) : ViewModel() {

    private val homeState = HomeState()
    val liveData = MutableLiveData<HomeStateBind>(homeState)

    fun start() {
        getCompanyList()
    }

    private fun getCompanyList() {
        viewModelScope.launch {
            val collectedData = getCompanies(GetCompanies.Request())
            collectedData.collect {
                when (it) {
                    is GetCompanies.Response.Success -> {
                        homeState.update(HomeAction.UpdateCompany(it.companyList))
                        liveData.postValue(homeState)
                    }
                    is GetCompanies.Response.Failure -> {
                        homeState.update(HomeAction.ShowToast(it.exception.message.toString()))
                        liveData.postValue(homeState)
                    }
                }
            }
        }
    }

    private fun getFundsFromCompany(companyTabSelectedIndex: Int) {
        viewModelScope.launch {
            val company = homeState.companyList?.getValue()?.get(companyTabSelectedIndex)
            val companyId = company?.id ?: -1
            val collectedFunds =
                getFundListFromCompany(GetFundListFromCompany.Request(companyId))
            collectedFunds.collect {
                when (it) {
                    is GetFundListFromCompany.Response.Success -> {
                        val action = HomeAction.UpdateCompanyFund(it.funds)
                        homeState.update(action)
                        liveData.postValue(homeState)
                    }
                    is GetFundListFromCompany.Response.Failure -> {
                        homeState.update(HomeAction.ShowToast(it.exception.message.toString()))
                        liveData.postValue(homeState)
                    }
                }
            }
        }
    }

    fun onCompanyTabSelected(tabPosition: Int) {
        debugLog(tabPosition)
        getFundsFromCompany(tabPosition)
    }
}