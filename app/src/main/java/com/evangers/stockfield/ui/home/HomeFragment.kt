package com.evangers.stockfield.ui.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentHomeBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.home.adapter.FundPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : StockFieldFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    lateinit var fundPagerAdapter: FundPagerAdapter

    var binding: FragmentHomeBinding? = null

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        initUi()
        initBinding()
        viewModel.start()
    }

    override fun bindView(view: View) {
        binding = FragmentHomeBinding.bind(view)
    }

    override fun unbindView() {
        binding = null
    }

    override fun initUi() {
        binding?.run {
            companySpinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {
                    }

                    override fun onItemSelected(
                        parent: AdapterView<*>?,
                        view: View?,
                        position: Int,
                        id: Long
                    ) {
                        viewModel.onCompanyTabSelected(position)
                    }
                }
            fundPagerAdapter = FundPagerAdapter(viewModel, this@HomeFragment)
            fundViewpager.adapter = fundPagerAdapter
            TabLayoutMediator(fundTab, fundViewpager) { tab, position ->
                tab.text = fundPagerAdapter.getFundName(position)
            }.attach()
        }
    }

    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner, { state ->
            state.companyList?.getValueIfNotHandled()?.let {
                binding?.companySpinner?.apply {
                    val adapter = ArrayAdapter<String>(
                        requireContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        it.map { it.name }
                    )
                    this.adapter = adapter
                }
            }
            state.companyFundList?.getValueIfNotHandled()?.let {
                fundPagerAdapter.replaceFundList(it)
            }
            state.fundHoldings?.getValueIfNotHandled()?.let {

            }
            state.toastMessage?.getValueIfNotHandled()?.let {
                binding?.tempText?.text = it
            }
            state.isLoading?.getValueIfNotHandled()?.let { isLoading ->
                binding?.includedLoadingBar?.loadingBarView?.isVisible = isLoading
            }
        })

    }


}