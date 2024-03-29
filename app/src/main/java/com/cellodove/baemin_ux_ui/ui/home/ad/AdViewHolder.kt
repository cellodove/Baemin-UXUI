package com.cellodove.baemin_ux_ui.ui.home.ad

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.cellodove.baemin_ux_ui.databinding.AdViewItemBinding

class AdViewHolder(private val binding: AdViewItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(image : Int){
        Glide.with(binding.root)
            .load(image)
            .into(binding.viewPagerImage)
    }


    companion object{
        fun create(parent : ViewGroup) : AdViewHolder{
            val layoutInflater = LayoutInflater.from(parent.context)
            return AdViewHolder(AdViewItemBinding.inflate(layoutInflater,parent,false))
        }

    }
}