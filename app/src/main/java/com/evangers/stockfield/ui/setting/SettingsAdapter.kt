package com.evangers.stockfield.ui.setting

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import androidx.recyclerview.widget.SortedListAdapterCallback
import com.evangers.stockfield.ui.setting.presenter.Presenter
import com.evangers.stockfield.ui.util.createView
import javax.inject.Inject

class SettingsAdapter @Inject constructor(
    private val presenter: Presenter
) : RecyclerView.Adapter<SettingsViewHolder>() {
    private val list = SortedList(
        SettingsItem::class.java,
        object : SortedListAdapterCallback<SettingsItem>(this) {
            override fun areItemsTheSame(item1: SettingsItem, item2: SettingsItem): Boolean {
                return item1.id == item2.id
            }

            override fun compare(o1: SettingsItem, o2: SettingsItem): Int {
                return o1.id - o2.id
            }

            override fun areContentsTheSame(oldItem: SettingsItem, newItem: SettingsItem): Boolean {
                return oldItem == newItem
            }
        }
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingsViewHolder {
        val view = parent.createView(viewType)
        return SettingsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size()
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].layoutId
    }

    override fun onBindViewHolder(holder: SettingsViewHolder, position: Int) {
        holder.onBind(presenter, list[position])
    }

    fun update(newList: List<SettingsItem>) {
        list.replaceAll(newList)
    }
}

class SettingsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun onBind(presenter: Presenter, item: SettingsItem) {
        item.presentUi(presenter, itemView)
    }
}