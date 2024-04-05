package com.cellodove.baemin_ux_ui.ui.home

import androidx.annotation.DrawableRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellodove.baemin_ux_ui.R
import com.cellodove.baemin_ux_ui.ui.common.SaleDeliveryData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel : ViewModel() {

    private val _couponExist = MutableLiveData<Boolean>().apply {
        value = Random.nextBoolean()
    }
    val couponExist: LiveData<Boolean> = _couponExist

    private val adImageDataList = arrayListOf<Int>(
        R.drawable.ad_image1,
        R.drawable.ad_image2,
        R.drawable.ad_image3,
        R.drawable.ad_image4,
        R.drawable.ad_image5,
        R.drawable.ad_image6,
        R.drawable.ad_image7,
        R.drawable.ad_image8,
        R.drawable.ad_image9,
        R.drawable.ad_image10,
        R.drawable.ad_image11,
        R.drawable.ad_image12,
        R.drawable.ad_image13,
        R.drawable.ad_image14,
        R.drawable.ad_image15
    )

    private val _adImageList = MutableLiveData<ArrayList<Int>>().apply {
        viewModelScope.launch {
            delay(500)
            value = adImageDataList
        }
    }

    val adImageList: LiveData<ArrayList<Int>> = _adImageList








    private val saleDeliveryDataList = arrayListOf<SaleDeliveryData>(
        SaleDeliveryData(R.drawable.sale_delivery_image1, 10, "고메 커피", "4.5" , "25~40분", "0원~3000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image2, 10, "낙곱새의 정석", "4.1" , "25~40분", "0원~3000원", true,false),
        SaleDeliveryData(R.drawable.sale_delivery_image3, 10, "오리지널팬케이크", "4.2" , "25~40분", "0원~4000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image4, 10, "칠리로29", "4.5" , "10~30분", "1000원~3000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image5, 10, "킹콩부대찌개", "4.3" , "25~40분", "0원~3000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image6, 10, "샐러디", "4.7" , "40~60분", "0원~3000원", true,false),
        SaleDeliveryData(R.drawable.sale_delivery_image7, 10, "이디야 판교", "3.5" , "25~40분", "1000원~3000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image8, 10, "달콤왕가탕후루", "3.4" , "15~40분", "0원~3000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image9, 10, "판교매일식당 본점", "4.5" , "20~40분", "0원~3000원", true,false),
        SaleDeliveryData(R.drawable.sale_delivery_image10, 10, "전주현대옥 판교점", "4.5" , "35~40분", "0원~3000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image11, 10, "아티산마티니", "4.5" , "25~40분", "0원~7000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image12, 10, "슬로우캘리 판교점", "2.5" , "17~33분", "0원~3000원", true,false),
        SaleDeliveryData(R.drawable.sale_delivery_image13, 10, "파리바게뜨 랩오더 판교점", "4.5" , "25~40분", "1000원~3000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image14, 10, "삼선회관 김치찌개", "4.1" , "18~36분", "0원~3000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image15, 10, "폴트버거 판교점", "4.7" , "25~40분", "0원~3000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image16, 10, "잭슨피자 판교점", "4.4" , "30~60분", "1000원~3000원", true,true),
        SaleDeliveryData(R.drawable.sale_delivery_image17, 10, "써든스테이크 판교점", "4.5" , "25~40분", "0원~3000원", true,false),
    )


    private val _saleDeliveryList = MutableLiveData<ArrayList<SaleDeliveryData>>().apply {
        viewModelScope.launch {
            delay(7000)
            value = saleDeliveryDataList
        }
    }

    val saleDeliveryList: LiveData<ArrayList<SaleDeliveryData>> = _saleDeliveryList

}