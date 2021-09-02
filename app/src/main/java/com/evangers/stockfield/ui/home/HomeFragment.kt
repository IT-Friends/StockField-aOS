package com.evangers.stockfield.ui.home

import android.os.Bundle
import android.util.DisplayMetrics
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
import com.evangers.stockfield.ui.util.debugLog
import com.evangers.stockfield.ui.util.showToast
import com.google.android.gms.ads.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : StockFieldFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var fundPagerAdapter: FundPagerAdapter

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adView: AdView
    private var initialLayoutComplete = false

    private val adSize: AdSize
        get() {
            val display = requireActivity().windowManager.defaultDisplay
            val outMetrics = DisplayMetrics()
            display.getMetrics(outMetrics)

            val density = outMetrics.density

            var adWidthPixels = binding.adViewContainer.width.toFloat()
            if (adWidthPixels == 0f) {
                adWidthPixels = outMetrics.widthPixels.toFloat()
            }

            val adWidth = (adWidthPixels / density).toInt()
            return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                requireContext(),
                adWidth
            )
        }


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
        initialLayoutComplete = false
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
            TabLayoutMediator(fundTab, fundViewpager) { tab, position ->
                tab.text = fundPagerAdapter.getFundName(position)
            }.attach()

            adView = AdView(requireContext())
            adViewContainer.addView(adView)
            adViewContainer.viewTreeObserver.addOnGlobalLayoutListener {
                if (!initialLayoutComplete) {
                    initialLayoutComplete = true
                    loadBanner()
                }
            }
        }
    }


    private fun loadBanner() {
        adView.adUnitId = getString(R.string.admob_home_banner_unit_id)
        adView.adSize = adSize
        val adRequest = AdRequest
            .Builder()
            .build()
        adView.loadAd(adRequest)
        adView.adListener = object : AdListener() {
            override fun onAdClosed() {
                debugLog("onAdClosed")
                super.onAdClosed()
            }

            override fun onAdFailedToLoad(error: LoadAdError) {
                debugLog("onAdFailedToLoad ${error.message}")
                super.onAdFailedToLoad(error)
            }

            override fun onAdOpened() {
                debugLog("onAdOpened")
                super.onAdOpened()
            }

            override fun onAdLoaded() {
                debugLog("onAdLoaded")
                super.onAdLoaded()
            }

            override fun onAdClicked() {
                debugLog("onAdClicked")
                super.onAdClicked()
            }

            override fun onAdImpression() {
                debugLog("onAdImpression")
                super.onAdImpression()
            }
        }
    }


    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner, { state ->
            state.companyList?.getValue()?.let {
                binding.companySpinner.apply {
                    val adapter = ArrayAdapter(
                        requireContext(),
                        R.layout.support_simple_spinner_dropdown_item,
                        it.map { it.name }
                    )
                    this.adapter = adapter
                }
            }
            state.companyFundList?.let { fundPagerAdapter.replaceFundList(it) }
            if (fundPagerAdapter.replaceFragmentList(state.fragmentList)) {
                binding.fundViewpager.setCurrentItem(state.currentFundTabPosition, false)
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
                val tickerName = it.first
                val displayName = it.second
                val fundName = fundPagerAdapter.getFundName(state.currentFundTabPosition)
                val navAction =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(
                        tickerName,
                        displayName,
                        fundName
                    )
                findNavController().navigate(navAction)
            }
        })

    }

    override fun onDestroyViewSf() {
        lifecycle.removeObserver(viewModel)
        binding.fundViewpager.adapter = null
        adView.destroy()
        super.onDestroyViewSf()
    }

    override fun onResume() {
        super.onResume()
        adView.resume()
    }

    override fun onPause() {
        adView.pause()
        super.onPause()
    }
}