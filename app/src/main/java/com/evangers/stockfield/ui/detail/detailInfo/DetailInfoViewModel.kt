package com.evangers.stockfield.ui.detail.detailInfo

import android.graphics.Color
import android.graphics.Paint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.domain.usecase.GetStockDetail
import com.evangers.stockfield.domain.usecase.GetStockPriceHistory
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailInfoViewModel @Inject constructor(
    private val getStockDetail: GetStockDetail,
    private val getStockPriceHistory: GetStockPriceHistory
) : ViewModel() {

    private val state = DetailInfoState()
    val liveData = MutableLiveData<DetailInfoStateBind>(state)

    fun start(ticker: String, fundName: String) {
        getDetail(ticker)
        getHistory(ticker, 1)
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

    private fun getHistory(ticker: String, page: Int) {
        viewModelScope.launch {
            setChartLoading(true)
            getStockPriceHistory(
                GetStockPriceHistory.Request(
                    ticker = ticker,
                    page = page,
                    order = "asc"
                )
            ).collect {
                withContext(Dispatchers.IO) {
                    when (it) {
                        is GetStockPriceHistory.Response.Success -> {
                            val dataSet = CandleDataSet(
                                it.list.mapIndexed { index, model ->
                                    CandleEntry(
                                        index.toFloat(),
                                        model.highPrice.toFloat(),
                                        model.lowPrice.toFloat(),
                                        model.openPrice.toFloat(),
                                        model.closePrice.toFloat()
                                    )
                                },
                                "price"
                            ).apply {
                                color = Color.rgb(80, 80, 80)
                                decreasingColor = Color.rgb(47, 139, 208)
                                increasingColor = Color.rgb(227, 68, 57)
                                color = Color.GRAY
                                shadowColor = Color.rgb(80, 80, 80)
                                shadowWidth = 0.8f
                                decreasingPaintStyle = Paint.Style.FILL
                                increasingPaintStyle = Paint.Style.FILL
                                neutralColor = Color.LTGRAY;
                                setDrawValues(false)
                                highLightColor = Color.TRANSPARENT
                            }
                            val data = CandleData(dataSet)
                            state.update(DetailInfoAction.UpdateHistoryData(page, data))
                            liveData.postValue(state)
                        }
                        is GetStockPriceHistory.Response.Failure -> {

                        }
                    }
                    setChartLoading(false)
                }
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