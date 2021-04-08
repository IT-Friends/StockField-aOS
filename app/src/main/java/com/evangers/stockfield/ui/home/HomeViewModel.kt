package com.evangers.stockfield.ui.home

import androidx.lifecycle.LifecycleObserver
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
) : ViewModel(), HomeController, LifecycleObserver {

    private val state = HomeState()
    val liveData = MutableLiveData<HomeStateBind>(state)

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
                        state.update(HomeAction.UpdateCompany(it.companyList))
                        liveData.postValue(state)
                    }
                    is GetCompanies.Response.Failure -> {
                        state.update(HomeAction.ShowToast(it.exception.message.toString()))
                        liveData.postValue(state)
                    }
                }
                setLoading(false)
            }
        }
    }

    private fun getFundsFromCompany(companyTabSelectedIndex: Int) {
        viewModelScope.launch {
            setLoading(true)
            val company = state.companyList?.getValue()?.get(companyTabSelectedIndex)
            val companyId = company?.id ?: -1
            val collectedFunds =
                getFundListFromCompany(GetFundListFromCompany.Request(companyId))
            collectedFunds.collect {
                when (it) {
                    is GetFundListFromCompany.Response.Success -> {
                        val action = HomeAction.UpdateCompanyFund(this@HomeViewModel, it.funds)
                        state.update(action)
                        liveData.postValue(state)
                    }
                    is GetFundListFromCompany.Response.Failure -> {
                        state.update(HomeAction.ShowToast(it.exception.message.toString()))
                        liveData.postValue(state)
                    }
                }
                setLoading(false)
            }
        }
    }

    fun onCompanyTabSelected(tabPosition: Int) {
        debugLog(tabPosition)
        if (state.currentSpinnerPosition != tabPosition) {
            state.currentSpinnerPosition = tabPosition
            getFundsFromCompany(tabPosition)
        }
    }

    private fun setLoading(isLoading: Boolean) {
        state.update(HomeAction.UpdateLoadingState(isLoading))
        liveData.postValue(state)
    }

    override fun onDateUpdate(pair: Pair<Int, String>) {
        state.update(HomeAction.UpdateDate(pair))
        liveData.postValue(state)
        displayDate()
    }

    override fun onUpdateLoadingState(isLoading: Boolean) {
        setLoading(isLoading)
    }

    override fun onStockClicked(ticker: String, displayName: String) {
        state.update(HomeAction.NavToDetail(ticker, displayName))
        liveData.postValue(state)
    }

    fun onFundTabSelected(position: Int?) {
        state.currentFundTabPosition = position ?: 0
        displayDate()
    }

    fun displayDate() {
        state.update(HomeAction.DisplayDate(state.currentFundTabPosition))
        liveData.postValue(state)
    }
}