package com.cellodove.baemin_ux_ui.ui.home.ad

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter

class AdPagerAdapter : ListAdapter<Int,AdViewHolder>(DATA_COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        return AdViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {
        holder.bind(getItem(position % currentList.size))
    }

    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    companion object{
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<Int>(){
            override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem

            override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean = oldItem == newItem
        }
    }
}