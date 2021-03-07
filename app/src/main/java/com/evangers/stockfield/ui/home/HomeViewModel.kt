package com.evangers.stockfield.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.domain.usecase.GetCompanies
import com.evangers.stockfield.domain.usecase.GetFundListFromCompany
import com.evangers.stockfield.ui.fundholdings.HomeController
import com.evangers.stockfield.ui.util.debugLog
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCompanies: GetCompanies,
    private val getFundListFromCompany: GetFundListFromCompany
) : ViewModel(), HomeController {

    private val homeState = HomeState()
    val liveData = MutableLiveData<HomeStateBind>(homeState)

    fun start() {
        getCompanyList()
    }

    private fun getCompanyList() {
        viewModelScope.launch {
            setLoading(true)
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
                setLoading(false)
            }
        }
    }

    private fun getFundsFromCompany(companyTabSelectedIndex: Int) {
        viewModelScope.launch {
            setLoading(true)
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
                setLoading(false)
            }
        }
    }

    fun onCompanyTabSelected(tabPosition: Int) {
        debugLog(tabPosition)
        getFundsFromCompany(tabPosition)
    }

    private fun setLoading(isLoading: Boolean) {
        homeState.update(HomeAction.UpdateLoadingState(isLoading))
        liveData.postValue(homeState)
    }

    override fun onDateUpdate(text: String) {
        homeState.update(HomeAction.UpdateDate(text))
        liveData.postValue(homeState)
    }

    override fun onUpdateLoadingState(isLoading: Boolean) {
        setLoading(isLoading)
    }
}