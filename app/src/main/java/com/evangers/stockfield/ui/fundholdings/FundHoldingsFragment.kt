package com.evangers.stockfield.ui.fundholdings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentFundholdingsBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.fundholdings.adapter.FundHoldingsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FundHoldingsFragment constructor(
    private val homeController: HomeController
) : StockFieldFragment(R.layout.fragment_fundholdings) {

    private val viewModel: FundHoldingsViewModel by viewModels()

    private val fundHoldingsAdapter = FundHoldingsAdapter(homeController)

    private lateinit var bindings: FragmentFundholdingsBinding

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        initUi()
        initBinding()
        val fundName: String = arguments?.getString(getString(R.string.fundNameKey)) ?: ""
        viewModel.start(fundName)
    }

    override fun initUi() {
        bindings.fundRecyclerView.apply {
            this.adapter = fundHoldingsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner, { state ->
            state.fundHoldings?.getValueIfNotHandled()?.let {
                fundHoldingsAdapter.replaceItems(it)
                val properText = Regex("\\d{4}-\\d{2}-\\d{2}").find(it.first().dateTo ?: "", 0)
                homeController.onDateUpdate(getString(R.string.dateFormat, properText?.value))
            }
            state.isLoading?.getValueIfNotHandled()?.let {
                homeController.onUpdateLoadingState(it)
            }
        })
    }

    override fun bindView(view: View) {
        bindings = FragmentFundholdingsBinding.bind(view)
    }
}