package com.evangers.stockfield.ui.detail.detailPageAdapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import javax.inject.Inject

class DetailPageAdapter @Inject constructor(
    private val fragmentList: List<Fragment>,
    fragment: Fragment
) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

}
