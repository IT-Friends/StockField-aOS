package com.evangers.stockfield.ui.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentHomeBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.home.adapter.FundPagerAdapter
import com.evangers.stockfield.ui.util.showToast
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : StockFieldFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var fundPagerAdapter: FundPagerAdapter

    private lateinit var binding: FragmentHomeBinding

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        initUi()
        initBinding()
        lifecycle.addObserver(viewModel)
        viewModel.start()
    }


    override fun bindView(view: View) {
        binding = FragmentHomeBinding.bind(view)
    }

    override fun initUi() {
        binding.run {
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
            fundTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                var init = false
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (init.not()) {
                        init = true
                        return
                    }
                    val position = tab?.position
                    viewModel.onFundTabSelected(position)
                    viewModel.displayDate()
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
            fundPagerAdapter = FundPagerAdapter(childFragmentManager, lifecycle)
            fundViewpager.adapter = fundPagerAdapter
            fundViewpager.offscreenPageLimit = 10
            TabLayoutMediator(fundTab, fundViewpager) { tab, position ->
                tab.text = fundPagerAdapter.getFundName(position)
            }.attach()
        }
    }

    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner, { state ->
            state.companyList?.getValueIfNotHandled()?.let {
                binding.companySpinner.apply {
                    val adapter = ArrayAdapter<String>(
                        requireContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        it.map { it.name }
                    )
                    this.adapter = adapter
                }
            }
            state.companyFundList?.let { fundPagerAdapter.replaceFundList(it) }
            if (fundPagerAdapter.replaceFragmentList(state.fragmentList)) {
                binding.fundViewpager.postDelayed({
                    binding.fundViewpager.setCurrentItem(state.currentFundTabPosition, false)
                }, 100)
            }
            state.toastMessage?.getValueIfNotHandled()?.let {
                showToast(it)
            }
            state.dateText?.let {
                binding.filterView.dateInfo.text = it
            }
            state.isLoading?.getValueIfNotHandled()?.let { isLoading ->
                binding.includedLoadingBar.loadingBarView.isVisible = isLoading
            }
            state.navToDetail?.getValueIfNotHandled()?.let {
                val navAction =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.first, it.second)
                findNavController().navigate(navAction)
            }
        })

    }

    override fun onDestroyViewSf() {
        super.onDestroyViewSf()
        lifecycle.removeObserver(viewModel)
    }
}