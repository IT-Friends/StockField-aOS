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
import javax.inject.Inject

@AndroidEntryPoint
class FundHoldingsFragment : StockFieldFragment(R.layout.fragment_fundholdings) {

    private val viewModel: FundHoldingsViewModel by viewModels()

    @Inject
    lateinit var fundHoldingsAdapter: FundHoldingsAdapter

    var bindings: FragmentFundholdingsBinding? = null

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        initUi()
        initBinding()
        val fundName: String = arguments?.getString("fundName") ?: ""
        viewModel.start(fundName)
    }

    override fun initUi() {
        bindings?.fundRecyclerView?.apply {
            this.adapter = fundHoldingsAdapter
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }
    }

    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner, { state ->
            state.fundHoldings?.getValueIfNotHandled()?.let {
                fundHoldingsAdapter.replaceItems(it)
                val ss = Regex("\\d{4}-\\d{2}-\\d{2}").find(it.first().dateTo ?: "", 0)
                bindings?.dateInfo?.text = getString(R.string.dateFormat, ss?.value)
            }
        })
    }

    override fun bindView(view: View) {
        bindings = FragmentFundholdingsBinding.bind(view)
    }

    override fun unbindView() {
        bindings = null
    }

}