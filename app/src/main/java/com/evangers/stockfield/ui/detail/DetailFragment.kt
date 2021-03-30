package com.evangers.stockfield.ui.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentDetailBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.util.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : StockFieldFragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var binding: FragmentDetailBinding

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        initUi()
        initBinding()
        val fromBundle = DetailFragmentArgs.fromBundle(requireArguments())

        val ticker = fromBundle.StringTickerKey
        val displayName = fromBundle.StringDisplayNameKey
        viewModel.start(ticker, displayName)
    }

    override fun bindView(view: View) {
        binding = FragmentDetailBinding.bind(view)
    }

    override fun initUi() {
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
        })

    }


}