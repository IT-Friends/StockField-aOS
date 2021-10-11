package com.evangers.stockfield.ui.stockdetail

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.LayoutFunddetailTableRowBinding
import com.evangers.stockfield.domain.model.HistoryModel
import com.evangers.stockfield.ui.util.applyDifference
import com.evangers.stockfield.ui.util.createView
import javax.inject.Inject

class FundHistoryAdapter @Inject constructor(
) : RecyclerView.Adapter<FundHistoryViewHolder>() {
    private var historyModelList = listOf<HistoryModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundHistoryViewHolder {
        val view = parent.createView(R.layout.layout_funddetail_table_row)
        return FundHistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: FundHistoryViewHolder, position: Int) {
        holder.onBind(historyModelList[position])
    }

    override fun getItemCount(): Int {
        return historyModelList.size
    }

    fun replaceItems(list: List<HistoryModel>) {
        historyModelList = list
    }

}

class FundHistoryViewHolder constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    private val binding = LayoutFunddetailTableRowBinding.bind(itemView)
    fun onBind(historyModel: HistoryModel) {
        with(binding) {
            dateContent.text = historyModel.date
            sharesContent.text = historyModel.shares.toString()
            sharesDifference.applyDifference(historyModel.sharesDifference)
            weightContent.text = historyModel.weight.toString()
            weightDifference.applyDifference(historyModel.weightDifference)
        }
    }

}