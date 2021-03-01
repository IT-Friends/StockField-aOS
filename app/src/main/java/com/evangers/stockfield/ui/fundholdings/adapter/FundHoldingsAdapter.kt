package com.evangers.stockfield.ui.fundholdings.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.LayoutStocktableRowBinding
import com.evangers.stockfield.domain.model.FundHoldingComparisonModel
import com.evangers.stockfield.ui.util.applyDifference
import java.text.NumberFormat
import javax.inject.Inject

class FundHoldingsAdapter @Inject constructor(
) : RecyclerView.Adapter<FundHoldingViewHolder>() {

    private var fundHoldings = emptyList<FundHoldingComparisonModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FundHoldingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_stocktable_row, parent, false)
        return FundHoldingViewHolder(view)
    }

    override fun onBindViewHolder(holder: FundHoldingViewHolder, position: Int) {
        holder.bind(fundHoldings[position])
    }

    override fun getItemCount(): Int {
        return fundHoldings.size
    }

    fun replaceItems(list: List<FundHoldingComparisonModel>) {
        fundHoldings = list
    }
}


class FundHoldingViewHolder constructor(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: FundHoldingComparisonModel) {
        with(LayoutStocktableRowBinding.bind(itemView)) {
            rankContent.text = item.rank.toString()
            rankDifference.applyDifference(item.rankDifference)

            tickerContent.text = item.ticker
            tickerDifference.text = item.displayName

            sharesContent.text = NumberFormat.getNumberInstance().format(item.shares)
            sharesDifference.applyDifference(item.sharesDifference)

            val weight =
                itemView.context.getString(R.string.numberWithPercent, item.weight.toString())
            weightContent.text = weight
            weightDifference.applyDifference(item.weightDifference)

            val closingPrice =
                item.closingPrice?.run { NumberFormat.getNumberInstance().format(this) } ?: "0"
            val closingPriceText =
                itemView.context.getString(R.string.numberWithDollar, closingPrice)
            closingPriceContent.text = closingPriceText
            closingPriceDifference.applyDifference(
                item.closingPriceDifference,
                item.closingPriceChangePercent
            )
        }
    }

}

