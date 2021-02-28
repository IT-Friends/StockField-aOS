package com.evangers.stockfield.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.domain.usecase.GetCompanies
import com.evangers.stockfield.domain.usecase.GetFundHoldings
import com.evangers.stockfield.domain.usecase.GetFundList
import com.evangers.stockfield.ui.util.debugLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFundHoldings: GetFundHoldings,
    private val getCompanies: GetCompanies,
    private val getFundList: GetFundList
) : ViewModel() {

    private val homeState = HomeState()
    val liveData = MutableLiveData(homeState)

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

    private fun getFundsFromCompany(companyIndex: Int) {
        viewModelScope.launch {
            val collectedFunds = getFundList(GetFundList.Request(companyIndex))
            collectedFunds.collect {
                when (it) {
                    is GetFundList.Response.Success -> {
                        val action = HomeAction.UpdateCompanyFund(it.funds.fundList)
                        homeState.update(action)
                        liveData.postValue(homeState)
                    }
                    is GetFundList.Response.Failure -> {
                        homeState.update(HomeAction.ShowToast(it.exception.message.toString()))
                        liveData.postValue(homeState)
                    }
                }
            }
        }
    }

    private fun getFundHoldings(fundName: String) {
        viewModelScope.launch {
            val collectedFund = getFundHoldings(GetFundHoldings.Request(fundName))
            collectedFund.collect {
                when (it) {
                    is GetFundHoldings.Response.Success -> {
                        homeState.update(HomeAction.UpdateFund(it.fundHoldings))
                        liveData.postValue(homeState)
                    }
                    is GetFundHoldings.Response.Failure -> {
                        homeState.update(HomeAction.ShowToast(it.errorMessage))
                        liveData.postValue(homeState)

                    }
                }
            }
        }
    }

    fun onCompanySelected(position: Int) {
        debugLog(position)
        // TODO: 2/21/21 fundlist 가져오고, 각 펀드들 stock 가져오고
        getFundsFromCompany(position)
    }
}