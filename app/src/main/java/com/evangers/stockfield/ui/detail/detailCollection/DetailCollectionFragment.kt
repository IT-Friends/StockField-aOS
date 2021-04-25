package com.evangers.stockfield.ui.detail.detailCollection

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentDetailCollectionBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.util.showToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailCollectionFragment @Inject constructor(
) : StockFieldFragment(R.layout.fragment_detail_collection) {

    private val viewModel: DetailCollectionViewModel by viewModels()

    private lateinit var bindings: FragmentDetailCollectionBinding

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        initUi()
        initBinding()
        val ticker = requireArguments().getString(tickerKey) ?: ""
        viewModel.start(ticker)
    }

    override fun initUi() {

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
                    // TODO: 4/25/21 리싸이클러뷰 작성하기
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
        ): DetailCollectionFragment {
            return DetailCollectionFragment().apply {
                arguments = bundleOf(tickerKey to stockName)
            }
        }
    }
}