package com.evangers.stockfield.ui.stockdetail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentFundDetailBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.util.onBackPressedDispatcher
import com.evangers.stockfield.ui.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FundDetailFragment : StockFieldFragment(R.layout.fragment_fund_detail) {

    private val viewModel: FundDetailViewModel by viewModels()

    private lateinit var binding: FragmentFundDetailBinding

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
            toolbar.backButton.setOnClickListener { viewModel.onBackPressed() }
        }
    }

    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner, { state ->
            state.updateTitle?.getValueIfNotHandled()?.let {
                binding.toolbar.mainTitle.text = it.first
                binding.toolbar.subTitle.text = it.second
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