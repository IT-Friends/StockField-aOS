package com.evangers.stockfield.ui.detail.detailInfo

import android.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.domain.usecase.GetFundHistoryWithStock
import com.evangers.stockfield.domain.usecase.GetStockDetail
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailInfoViewModel @Inject constructor(
    private val getStockDetail: GetStockDetail,
    private val getFundHistoryWithStock: GetFundHistoryWithStock
) : ViewModel() {

    private val state = DetailInfoState()
    val liveData = MutableLiveData<DetailInfoStateBind>(state)

    fun start(ticker: String, fundName: String) {
        getDetail(ticker)
        getHistory(ticker, fundName, 0)
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

    private fun getHistory(ticker: String, fundName: String, page: Int) {
        viewModelScope.launch {
            setChartLoading(true)
            getFundHistoryWithStock(
                GetFundHistoryWithStock.Request(
                    page, ticker, fundName
                )
            ).collect {
                when (it) {
                    is GetFundHistoryWithStock.Response.Success -> {
                        val dataSet = LineDataSet(it.list.reversed().mapIndexed { index, historyModel ->
                            Entry(index.toFloat(), historyModel.shares.toFloat())
                        }, "shares")
                            .apply {
                                setDrawValues(false) // 터치시 노란 선 제거
                                highLightColor = Color.TRANSPARENT
                            }
                        val data = LineData(dataSet)
                        state.update(DetailInfoAction.UpdateHistoryData(page, data))
                        liveData.postValue(state)
                    }
                    is GetFundHistoryWithStock.Response.Failure -> {

                    }
                }
                setChartLoading(false)
            }
        }
    }


    private fun setChartLoading(isLoading: Boolean) {
        state.update(DetailInfoAction.UpdateChartLoadingState(isLoading))
        liveData.postValue(state)
    }

    private fun setLoading(isLoading: Boolean) {
        state.update(DetailInfoAction.UpdateLoadingState(isLoading))
        liveData.postValue(state)
    }

}