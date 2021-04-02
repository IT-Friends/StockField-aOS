package com.evangers.stockfield.ui.detail.detailPageAdapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.evangers.stockfield.R
import com.evangers.stockfield.ui.detail.detailInfo.DetailInfoFragment
import javax.inject.Inject

class DetailPageAdapter @Inject constructor(
    private val ticker: String,
    private val fragment: Fragment
) : FragmentStateAdapter(fragment) {


    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return DetailInfoFragment().apply {
            arguments = bundleOf(fragment.getString(R.string.tickerKey) to ticker)
        }
    }

}
