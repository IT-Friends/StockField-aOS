package com.evangers.stockfield.ui.fundholdings

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.domain.usecase.GetFundHoldingsFromFund
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FundHoldingsViewModel @Inject constructor(
    private val getFundHoldingsFromFund: GetFundHoldingsFromFund
) : ViewModel() {

    private val state = FundHoldingsState()
    val liveData = MutableLiveData<FundHoldingsStateBind>(state)

    fun start(fundName: String) {
        if (state.fundHoldings == null) {
            getFundHoldings(fundName)
        }
    }

    private fun getFundHoldings(fundName: String) {
        viewModelScope.launch {
            setLoading(true)
            val collectedData = getFundHoldingsFromFund(GetFundHoldingsFromFund.Request(fundName))
            collectedData.collect {
                when (it) {
                    is GetFundHoldingsFromFund.Response.Success -> {
                        state.update(FundHoldingsAction.UpdateFund(it.fundHoldings))
                        liveData.postValue(state)
                    }
                    is GetFundHoldingsFromFund.Response.Failure -> {
                        state.update(FundHoldingsAction.ShowToast(it.errorMessage))
                        liveData.postValue(state)
                    }
                }
                setLoading(false)
            }
        }
    }


    private fun setLoading(isLoading: Boolean) {
        state.update(FundHoldingsAction.UpdateLoadingState(isLoading))
        liveData.postValue(state)
    }

}