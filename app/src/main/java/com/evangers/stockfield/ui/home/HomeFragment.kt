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
        binding?.companySpinner?.onItemSelectedListener =
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
        })

    }


}