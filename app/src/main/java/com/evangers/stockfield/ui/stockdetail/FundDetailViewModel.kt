package com.evangers.stockfield.ui.stockdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FundDetailViewModel @Inject constructor(
) : ViewModel() {

    private val state = FundDetailState()
    val liveData = MutableLiveData<FundDetailStateBind>(state)

    fun start(ticker: String, fundName: String) {
        updateTitle(ticker, fundName)

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