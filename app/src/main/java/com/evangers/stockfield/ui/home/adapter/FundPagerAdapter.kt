package com.evangers.stockfield.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.evangers.stockfield.domain.model.FundModel

class FundPagerAdapter constructor(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private var fundList: List<FundModel> = emptyList()
        set(value) {
            field = value
        }

    private var fragmentList: List<Fragment> = listOf()

    fun replaceFundList(list: List<FundModel>) {
        fundList = list
    }

    fun replaceFragmentList(list: List<Fragment>): Boolean {
        if (fragmentList != list) {
            fragmentList = list
            notifyDataSetChanged()
            return true
        }
        return false
    }

    fun getFundName(position: Int): String = if (position < fundList.size)
        fundList[position].name
    else
        ""

    override fun getItemCount(): Int {
        return fundList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}