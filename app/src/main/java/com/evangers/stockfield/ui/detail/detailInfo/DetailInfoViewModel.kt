package com.evangers.stockfield.ui.detail.detailInfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.domain.usecase.GetStockDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailInfoViewModel @Inject constructor(
    private val getStockDetail: GetStockDetail
) : ViewModel() {

    private val state = DetailInfoState()
    val liveData = MutableLiveData<DetailInfoStateBind>(state)

    fun start(ticker: String) {
        getDetail(ticker)
    }

    private fun getDetail(ticker: String) {
        viewModelScope.launch {
            setLoading(true)
            getStockDetail(GetStockDetail.Request(ticker))
                .collect {
                    when (it) {
                        is GetStockDetail.Response.Success -> {
                            state.update(DetailInfoAction.UpdateDetail(it.stockModel))
                            liveData.postValue(state)
                        }
                        is GetStockDetail.Response.Failure -> {

                        }
                    }
                    setLoading(false)
                }
        }
    }


    private fun setLoading(isLoading: Boolean) {
        state.update(DetailInfoAction.UpdateLoadingState(isLoading))
        liveData.postValue(state)
    }

}