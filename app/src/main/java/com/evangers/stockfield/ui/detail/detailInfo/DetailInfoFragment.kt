package com.evangers.stockfield.ui.detail.detailInfo

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentDetailInfoBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.detail.DetailFragmentArgs
import com.evangers.stockfield.ui.util.applyDifference
import com.evangers.stockfield.ui.util.toText
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailInfoFragment @Inject constructor(
) : StockFieldFragment(R.layout.fragment_detail_info) {


    private val viewModel: DetailInfoViewModel by viewModels()

    private lateinit var bindings: FragmentDetailInfoBinding

    private val fromBundle by lazy {
        DetailFragmentArgs.fromBundle(requireArguments())
    }

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        initUi()
        initBinding()
        val stockName = fromBundle.tickerKey
        val fundName = fromBundle.fundNameKey
        viewModel.start(stockName, fundName)
    }

    override fun initUi() {


        with(bindings) {
            candleStickChart.axisLeft.run {
                setDrawGridLines(false)
                textColor = Color.WHITE
            }
            candleStickChart.axisRight.run {
                isEnabled = false
            }
            // X 축
            candleStickChart.xAxis.run {
                textColor = Color.TRANSPARENT
                setDrawAxisLine(false)
                setDrawGridLines(false)
                setAvoidFirstLastClipping(true)
            } // 범례
            candleStickChart.legend.run { isEnabled = false }


        }

    }

    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner, { state ->
            state.stockInfo?.getValueIfNotHandled()?.let {
                with(bindings) {
                    val properText = Regex("\\d{4}-\\d{2}-\\d{2}").find(it.regularMarketTime, 0)
                    dateText.text = getString(R.string.dateFormat, properText?.value)
//                    """
//                        ${it.industry}
//                        ${it.market}
//                        ${it.marketCap}
//                        ${it.sector}
//                    """
                    displayName.text = fromBundle.displayNameKey

                    price.text =
                        getString(R.string.numberWithDollar, it.regularMarketPrice.toString())
                    priceDiff.applyDifference(
                        it.regularMarketChange,
                        it.regularMarketChangePercent
                    )

                    totalPriceContent.text =
                        getString(R.string.numberWithDollar, it.regularMarketVolume.toString())
                    sectorContent.text = it.sector
                    industryContent.text = it.industry
                    highestPriceContent.text =
                        getString(R.string.numberWithDollar, it.fiftyTwoWeekHigh.toString())
                    lowestPriceContent.text =
                        getString(R.string.numberWithDollar, it.fiftyTwoWeekLow.toString())
                    perContent.text = it.priceEarningsRatio.toText()
                    roeContent.text = it.returnOnEquity.toText()
                    pbrContent.text = it.priceToBookRatio.toText()


                }
            }
            state.isChartLoading?.getValueIfNotHandled()?.let {
                bindings.chartLoadingBar.loadingBarView.isVisible = it
            }
            state.historyData?.getValueIfNotHandled()?.let {
                bindings.candleStickChart.apply {
                    this.data = it
                    description.isEnabled = false
                    isHighlightPerDragEnabled = true
                    requestDisallowInterceptTouchEvent(true)
                    invalidate()
                }
            }

        })
    }

    override fun bindView(view: View) {
        bindings = FragmentDetailInfoBinding.bind(view)
    }

    companion object {
        private const val tickerKey = "tickerKey"
        private const val displayNameKey = "displayNameKey"
        private const val fundNameKey = "fundNameKey"
        fun newInstance(
            stockName: String,
            displayName: String,
            fundName: String
        ): DetailInfoFragment {
            return DetailInfoFragment().apply {
                arguments = bundleOf(
                    tickerKey to stockName, displayNameKey to displayName,
                    fundNameKey to fundName
                )
            }
        }
    }
}