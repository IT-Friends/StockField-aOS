package com.evangers.stockfield.ui.detail.detailCollection

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentDetailCollectionBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.detail.DetailActionListener
import com.evangers.stockfield.ui.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailCollectionFragment @Inject constructor(
    private val detailFragmentActionListener: DetailActionListener
) : StockFieldFragment(R.layout.fragment_detail_collection) {

    private val viewModel: DetailCollectionViewModel by viewModels()

    private lateinit var bindings: FragmentDetailCollectionBinding
    private lateinit var fundCollectionAdapter: FundCollectionAdapter

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        initUi()
        initBinding()
        val ticker = requireArguments().getString(tickerKey) ?: ""
        viewModel.start(ticker)
    }

    override fun initUi() {
        fundCollectionAdapter = FundCollectionAdapter(viewModel)
        bindings.fundItemRecyclerView.let {
            it.layoutManager = LinearLayoutManager(requireContext())
            it.adapter = fundCollectionAdapter
        }
    }

    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner, { state ->
            with(bindings) {
                infoText.text = getString(R.string.etfHoldingsInfoFormat, state.fundTotalCount)
                state.toast?.getValueIfNotHandled()?.let {
                    showToast(getString(it))
                }
                state.isLoading?.getValueIfNotHandled()?.let {
                    loadingBar.loadingBarView.isVisible = it
                }
                state.fundList?.getValueIfNotHandled()?.let {
                    fundCollectionAdapter.replaceItem(it)
                }
                state.navToFundDetail?.getValueIfNotHandled()?.let { (ticker, fundName) ->
                    detailFragmentActionListener.navToFundDetail(ticker, fundName)
                }
            }
        })
    }

    override fun bindView(view: View) {
        bindings = FragmentDetailCollectionBinding.bind(view)
    }

    companion object {
        private const val tickerKey = "tickerKey"
        fun newInstance(
            stockName: String,
            detailFragmentActionListener: DetailActionListener
        ): DetailCollectionFragment {
            return DetailCollectionFragment(detailFragmentActionListener).apply {
                arguments = bundleOf(tickerKey to stockName)
            }
        }
    }
}