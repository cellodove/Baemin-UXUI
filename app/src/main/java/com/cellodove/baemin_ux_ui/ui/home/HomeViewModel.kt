package com.cellodove.baemin_ux_ui.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellodove.baemin_ux_ui.R
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

}