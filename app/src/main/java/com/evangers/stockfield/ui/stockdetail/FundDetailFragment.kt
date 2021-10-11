package com.evangers.stockfield.ui.stockdetail

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentFundDetailBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.util.onBackPressedDispatcher
import com.evangers.stockfield.ui.util.showShortToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FundDetailFragment : StockFieldFragment(R.layout.fragment_fund_detail) {

    private val viewModel: FundDetailViewModel by viewModels()

    private lateinit var binding: FragmentFundDetailBinding
    private val fundHistoryAdapter = FundHistoryAdapter()

    private val fromBundle by lazy {
        FundDetailFragmentArgs.fromBundle(requireArguments())
    }

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        initUi()
        initBinding()
        viewModel.start(fromBundle.ticker, fromBundle.fundName)
    }

    override fun bindView(view: View) {
        binding = FragmentFundDetailBinding.bind(view)
    }

    override fun initUi() {
        this.onBackPressedDispatcher { viewModel.onBackPressed() }
        with(binding) {
            fundHistoryRecyclerView.apply {
                layoutManager = LinearLayoutManager(requireContext())
                adapter = fundHistoryAdapter
            }
            toolbar.backButton.setOnClickListener { viewModel.onBackPressed() }
            chart.axisLeft.run {
                setDrawGridLines(false)
                textColor = Color.WHITE
            }
            chart.axisRight.run {
                isEnabled = false
            }
            // X 축
            chart.xAxis.run {
                textColor = Color.TRANSPARENT
                setDrawAxisLine(false)
                setDrawGridLines(false)
                setAvoidFirstLastClipping(true)
            } // 범례
            chart.legend.run { isEnabled = false }


        }
    }

    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner, { state ->
            state.updateTitle?.getValueIfNotHandled()?.let {
                binding.toolbar.mainTitle.text = it.first
                binding.toolbar.subTitle.text = it.second
            }
            state.toastMessage?.getValueIfNotHandled()?.let {
                showShortToast(it)
            }
            state.isLoading?.getValueIfNotHandled()?.let { isLoading ->
                binding.includedLoadingBar.loadingBarView.isVisible = isLoading
            }
            state.fundHistoryList?.getValueIfNotHandled()?.let {
                fundHistoryAdapter.replaceItems(it)
            }
            state.navToBack?.getValueIfNotHandled()?.let {
                findNavController().popBackStack()
            }
            state.lineChartList?.getValueIfNotHandled()?.let {
                binding.chart.apply {
                    data = it
                    description.isEnabled = false
                    isHighlightPerDragEnabled = true
                    requestDisallowInterceptTouchEvent(true)
                    invalidate()
                }

            }
        })
    }


}