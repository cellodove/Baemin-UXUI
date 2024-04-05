package com.cellodove.baemin_ux_ui.ui.common

import androidx.annotation.DrawableRes

data class SaleDeliveryData(
    @DrawableRes
    val titleImage : Int,

    val discountCoupon : Int,
    val storeName : String,
    val storeRate : String,
    val deliveryTime : String,
    val deliveryExpense : String,
    val isDeliveryShare : Boolean,
    val isOneHouseDelivery : Boolean

)