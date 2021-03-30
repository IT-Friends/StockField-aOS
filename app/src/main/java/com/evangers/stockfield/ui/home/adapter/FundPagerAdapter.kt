package com.evangers.stockfield.ui.home.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.evangers.stockfield.R
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.ui.fundholdings.FundHoldingsFragment
import com.evangers.stockfield.ui.fundholdings.HomeController

class FundPagerAdapter constructor(
    private val homeController: HomeController,
    private val fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private var fundList: List<FundModel> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    fun replaceFundList(list: List<FundModel>) {
        fundList = list
    }

    fun getFundName(position: Int): String = if (position < fundList.size)
        fundList[position].name
    else
        ""

    override fun getItemCount(): Int {
        return fundList.size
    }

    override fun createFragment(position: Int): Fragment {
        return FundHoldingsFragment(
            homeController
        ).apply {
            val fundNameKey = fragment.getString(R.string.fundNameKey)
            arguments = bundleOf(fundNameKey to fundList[position].name)
        }
    }

}