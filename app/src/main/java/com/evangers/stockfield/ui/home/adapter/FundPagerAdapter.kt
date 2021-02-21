package com.evangers.stockfield.ui.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.evangers.stockfield.R
import javax.inject.Inject

class FundPagerAdapter @Inject constructor(
    fragment: Fragment
) : FragmentStateAdapter(fragment) {

    private var fundList: List<String> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    fun replaceFundList(list: List<String>) {
        fundList = list
    }

    fun getFundName(position: Int): String = if (position < fundList.size)
        fundList[position]
    else
        ""


    override fun getItemCount(): Int {
        return fundList.size
    }

    override fun createFragment(position: Int): Fragment {
        return Fragment(R.layout.fragment_fund)
    }

}