package com.evangers.stockfield.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentDetailBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.detail.detailInfo.DetailInfoFragment
import com.evangers.stockfield.ui.detail.detailPageAdapter.DetailPageAdapter
import com.evangers.stockfield.ui.util.onBackPressedDispatcher
import com.evangers.stockfield.ui.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : StockFieldFragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: FragmentDetailBinding

    private lateinit var detailPageAdapter: DetailPageAdapter
    private val fromBundle by lazy {
        DetailFragmentArgs.fromBundle(requireArguments())
    }

    private val fragmentList = lazy {
        listOf(
            DetailInfoFragment.newInstance(
                fromBundle.tickerKey,
                fromBundle.displayNameKey
            )
        )
    }

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        initUi()
        initBinding()
        viewModel.start(fromBundle.tickerKey, fromBundle.displayNameKey)
    }

    override fun bindView(view: View) {
        binding = FragmentDetailBinding.bind(view)
    }

    override fun initUi() {
        this.onBackPressedDispatcher { viewModel.onBackPressed() }
        with(binding) {
            detailPageAdapter = DetailPageAdapter(fragmentList.value, this@DetailFragment)
            detailViewPager.adapter = detailPageAdapter
            detailViewPager.isUserInputEnabled = false
            toolbar.backButton.setOnClickListener { viewModel.onBackPressed() }
        }
    }

    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner, { state ->
            state.updateTitle?.getValueIfNotHandled()?.let {
                binding.toolbar.mainTitle.text = it.first
            }
            state.toastMessage?.getValueIfNotHandled()?.let {
                showToast(it)
            }
            state.isLoading?.getValueIfNotHandled()?.let { isLoading ->
                binding.includedLoadingBar.loadingBarView.isVisible = isLoading
            }
            state.navToBack?.getValueIfNotHandled()?.let {
                findNavController().popBackStack()
            }
        })
    }


}