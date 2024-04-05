package com.cellodove.baemin_ux_ui.ui.home.adapter.saledelivery

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cellodove.baemin_ux_ui.databinding.SaleDeliveryItemBinding
import com.cellodove.baemin_ux_ui.ui.common.SaleDeliveryData


class SaleDeliveryViewHolder(private val binding: SaleDeliveryItemBinding) : RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("ClickableViewAccessibility")
    fun bind(saleDeliveryData : SaleDeliveryData){

        Glide.with(binding.root)
            .load(saleDeliveryData.titleImage)
            .into(binding.titleImage)

        binding.discountCoupon.text = "${saleDeliveryData.discountCoupon}% 중복할인"
        binding.storeName.text = saleDeliveryData.storeName
        binding.storeRate.text = saleDeliveryData.storeRate
        binding.deliveryTime.text = saleDeliveryData.deliveryTime
        binding.deliveryExpense.text = saleDeliveryData.deliveryExpense
        binding.deliveryShare.visibility = if (saleDeliveryData.isDeliveryShare) View.VISIBLE else View.GONE
        binding.oneHouseDelivery.visibility = if (saleDeliveryData.isOneHouseDelivery) View.VISIBLE else View.GONE

        binding.titleImage.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN){
                binding.titleImage.animate().scaleY(0.95F).duration = 500
                binding.titleImage.animate().scaleX(0.95F).duration = 500
            }
            if (event.action == MotionEvent.ACTION_UP || event.action == MotionEvent.ACTION_CANCEL){
                binding.titleImage.animate().scaleY(1F).duration = 500
                binding.titleImage.animate().scaleX(1F).duration = 500
            }
            false
        }

    }


    companion object{
        fun create(parent : ViewGroup) : SaleDeliveryViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            return SaleDeliveryViewHolder(SaleDeliveryItemBinding.inflate(layoutInflater,parent,false))
        }
    }
}