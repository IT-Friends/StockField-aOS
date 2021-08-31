package com.evangers.stockfield.ui.stockdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.domain.usecase.GetFundHistoryWithStock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FundDetailViewModel @Inject constructor(
    private val getFundHistoryWithStock: GetFundHistoryWithStock
) : ViewModel() {

    private val state = FundDetailState()
    val liveData = MutableLiveData<FundDetailStateBind>(state)

    fun start(ticker: String, fundName: String) {
        updateTitle(ticker, fundName)
        getStockHistoryFromFund(ticker, fundName)
    }

    private fun getStockHistoryFromFund(ticker: String, fundName: String) {
        viewModelScope.launch {
            getFundHistoryWithStock.invoke(GetFundHistoryWithStock.Request(0, ticker, fundName))
                .collect {
                    withContext(Dispatchers.IO) {
                        when (it) {
                            is GetFundHistoryWithStock.Response.Success -> {
                                state.update(FundDetailAction.UpdateFundHistory(it.total, it.list))
                                liveData.postValue(state)
                            }
                            is GetFundHistoryWithStock.Response.Failure -> {

                            }
                        }
                    }

                }

        }
    }

    private fun updateTitle(ticker: String, fundName: String) {
        state.update(FundDetailAction.UpdateTitle(ticker to fundName))
        liveData.postValue(state)
    }


    private fun setLoading(isLoading: Boolean) {
        state.update(FundDetailAction.UpdateLoadingState(isLoading))
        liveData.postValue(state)
    }

    fun onBackPressed() {
        state.update(FundDetailAction.NavToBack)
        liveData.postValue(state)
    }

}