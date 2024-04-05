package com.cellodove.baemin_ux_ui.ui.home.adapter.saledelivery

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cellodove.baemin_ux_ui.ui.common.SaleDeliveryData

class SaleDeliveryAdapter : ListAdapter<SaleDeliveryData,SaleDeliveryViewHolder>(DATA_COMPARATOR){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleDeliveryViewHolder {
        return SaleDeliveryViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: SaleDeliveryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DATA_COMPARATOR = object : DiffUtil.ItemCallback<SaleDeliveryData>(){
            override fun areItemsTheSame(
                oldItem: SaleDeliveryData,
                newItem: SaleDeliveryData
            ): Boolean {
                return oldItem.storeName == newItem.storeName
            }

            override fun areContentsTheSame(
                oldItem: SaleDeliveryData,
                newItem: SaleDeliveryData
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}