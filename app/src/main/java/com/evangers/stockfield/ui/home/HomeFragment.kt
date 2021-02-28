package com.evangers.stockfield.ui.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentHomeBinding
import com.evangers.stockfield.ui.base.SfFragment
import com.evangers.stockfield.ui.home.adapter.FundPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : SfFragment(R.layout.fragment_home) {

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
                        viewModel.onCompanySelected(position)
                    }
                }
            fundPagerAdapter = FundPagerAdapter(this@HomeFragment)
            fundViewpager.adapter = fundPagerAdapter
            TabLayoutMediator(fundTab, fundViewpager) { tab, position ->
                tab.text = fundPagerAdapter.getFundName(position)
            }.attach()
        }
    }

    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner, Observer { state ->
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
                val stringBuilder = StringBuilder()
                stringBuilder.append(
                    "dateTo\t" +
                            "ticker\t" +
                            "closingPrice\t" +
                            "closingPriceDifference\t" +
                            "shares\t" +
                            "sharesDifference\t" +
                            "weight\t" +
                            "weightDifference\t"
                )
                it.fundHoldingList.forEach { stock ->
                    stringBuilder.append(
                        "${stock.dateTo}\t ${stock.ticker}\t ${stock.closingPrice}\t ${stock.closingPriceDifference}\t ${stock.shares}\t ${stock.sharesDifference}\t ${stock.weight}\t ${stock.weightDifference}\t"
                    ).appendLine()
                }
                binding?.tempText?.text = stringBuilder.toString()
            }
            state.toastMessage?.getValueIfNotHandled()?.let {
                binding?.tempText?.text = it
            }
        })

    }


}