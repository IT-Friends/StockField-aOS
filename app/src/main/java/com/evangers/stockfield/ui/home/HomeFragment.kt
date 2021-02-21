package com.evangers.stockfield.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentHomeBinding
import com.evangers.stockfield.ui.base.SfFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : SfFragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

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
    }

    override fun initBinding() {
        viewModel.liveData.observe(viewLifecycleOwner) { state->

            state.fund?.getValueIfNotHandled()?.let {
                val stringBuilder = StringBuilder()
                stringBuilder.append(it.fundName).appendLine()
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
                it.stockList.forEach { stock ->
                    stringBuilder.append(
                        "${stock.dateTo}\t ${stock.ticker}\t ${stock.closingPrice}\t ${stock.closingPriceDifference}\t ${stock.shares}\t ${stock.sharesDifference}\t ${stock.weight}\t ${stock.weightDifference}\t"
                    ).appendLine()
                }
                binding?.tempText?.text = stringBuilder.toString()
            }
            state.toastMessage?.getValueIfNotHandled()?.let {
                binding?.tempText?.text = it
            }
        }

    }


}