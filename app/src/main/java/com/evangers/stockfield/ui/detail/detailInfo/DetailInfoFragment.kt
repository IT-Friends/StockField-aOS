package com.evangers.stockfield.ui.detail.detailInfo

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentDetailInfoBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.detail.DetailFragmentArgs
import com.evangers.stockfield.ui.util.applyDifference
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
        viewModel.start(stockName)
    }

    override fun initUi() {

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
                    perContent.text =
                        getString(R.string.numberWithDollar, it.priceEarningsRatio.toString())
                    roeContent.text =
                        getString(R.string.numberWithDollar, it.returnOnEquity.toString())
                    pbrContent.text =
                        getString(R.string.numberWithDollar, it.priceToBookRatio.toString())


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
        fun newInstance(stockName: String, displayName: String): DetailInfoFragment {
            return DetailInfoFragment().apply {
                arguments = bundleOf(tickerKey to stockName, displayNameKey to displayName)
            }
        }
    }
}