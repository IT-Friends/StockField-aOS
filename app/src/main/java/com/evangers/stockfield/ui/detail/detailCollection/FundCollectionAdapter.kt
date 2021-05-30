package com.evangers.stockfield.ui.detail.detailCollection

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.LayoutFundCollectionItemBinding
import com.evangers.stockfield.domain.model.FundModel
import com.evangers.stockfield.ui.util.createView
import com.evangers.stockfield.ui.util.debugLog
import com.evangers.stockfield.ui.util.showDrawable
import com.evangers.stockfield.ui.util.showUrl

class FundCollectionAdapter constructor(
    private val collectionClickListener: CollectionClickListener
) : RecyclerView.Adapter<FundCollectionViewHolder>() {
    private val sortedList =
        SortedList(FundModel::class.java, object : SortedListAdapterCallback<FundModel>(this) {
            override fun compare(o1: FundModel, o2: FundModel): Int {
                return o1.id - o2.id
            }

            override fun areContentsTheSame(oldItem: FundModel, newItem: FundModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areItemsTheSame(item1: FundModel, item2: FundModel): Boolean {
                return item1 == item2
            }

        })

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundCollectionViewHolder {
        val itemView = parent.createView(R.layout.layout_fund_collection_item)
        return FundCollectionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FundCollectionViewHolder, position: Int) {
        holder.onBind(sortedList[position], collectionClickListener)
    }

    override fun getItemCount(): Int {
        return sortedList.size()
    }

    fun replaceItem(list: List<FundModel>) {
        sortedList.replaceAll(list)
    }

}

class FundCollectionViewHolder constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val binding = LayoutFundCollectionItemBinding.bind(itemView)

    fun onBind(fundModel: FundModel, collectionClickListener: CollectionClickListener) {
        itemView.setOnClickListener {
            collectionClickListener.onClickCollection(fundModel.name)
        }
        with(binding) {
            if (fundModel.companyIconUrl == "https://stock-field.com/images/ark.jpg") {
                fundImage.showDrawable(R.drawable.ark_logo)
                debugLog("arkLogo shown")
            } else {
                fundImage.showUrl(fundModel.companyIconUrl)
            }
            companyName.text = fundModel.companyName
            fundName.text = fundModel.name
        }
    }
}