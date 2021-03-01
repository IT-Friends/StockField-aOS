package com.evangers.stockfield.ui.fundholdings

import android.os.Bundle
import android.view.View
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentFundholdingsBinding
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.util.debugLog

class FundHoldingsFragment : StockFieldFragment(R.layout.fragment_fundholdings) {

    var bindings: FragmentFundholdingsBinding? = null

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        initUi()
        initBinding()
        val fundName = arguments?.get("fundName")?:"empty"
        debugLog(fundName)
    }

    override fun initUi() {

    }

    override fun initBinding() {

    }

    override fun bindView(view: View) {
        bindings = FragmentFundholdingsBinding.bind(view)
    }

    override fun unbindView() {
        bindings = null
    }

}