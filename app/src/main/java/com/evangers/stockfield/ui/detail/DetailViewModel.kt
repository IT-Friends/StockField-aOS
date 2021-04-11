package com.evangers.stockfield.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
) : ViewModel() {

    private val state = DetailState()
    val liveData = MutableLiveData<DetailStateBind>(state)

    fun start(ticker: String, displayName: String) {
        updateTitle(ticker, displayName)
    }

    private fun updateTitle(ticker: String, displayName: String) {
        state.update(DetailAction.UpdateTitle(ticker to displayName))
        liveData.postValue(state)
    }


    private fun setLoading(isLoading: Boolean) {
        state.update(DetailAction.UpdateLoadingState(isLoading))
        liveData.postValue(state)
    }

    fun onBackPressed() {
        state.update(DetailAction.NavToBack)
        liveData.postValue(state)
    }

}