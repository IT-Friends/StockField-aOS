package com.evangers.stockfield.ui.detail.detailCollection

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.R
import com.evangers.stockfield.domain.usecase.GetFundListFromTicker
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCollectionViewModel @Inject constructor(
    private val getFundListFromTicker: GetFundListFromTicker
) : ViewModel() {

    private val state = DetailCollectionState()
    val liveData = MutableLiveData<DetailCollectionStateBind>(state)

    fun start(ticker: String) {
        getFunds(ticker)
    }

    private fun getFunds(ticker: String) {
        viewModelScope.launch {
            setLoading(true)
            getFundListFromTicker(GetFundListFromTicker.Request(ticker))
                .collect {
                    when (it) {
                        is GetFundListFromTicker.Response.Success -> {
                            state.update(DetailCollectionAction.UpdateFundList(it.total, it.funds))
                            liveData.postValue(state)
                        }
                        is GetFundListFromTicker.Response.Failure -> {
                            state.update(
                                DetailCollectionAction.ShowToast(R.string.failToLoading)
                            )
                            liveData.postValue(state)
                        }
                    }
                    setLoading(false)
                }
        }
    }

    private fun setLoading(isLoading: Boolean) {
        state.update(DetailCollectionAction.UpdateLoadingState(isLoading))
        liveData.postValue(state)
    }

}