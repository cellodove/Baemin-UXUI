package com.cellodove.baemin_ux_ui.ui.intro

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cellodove.baemin_ux_ui.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class IntroViewModel : ViewModel() {

    private val _image = MutableLiveData<Int>().apply {
        viewModelScope.launch {
            delay(300)
            value = R.drawable.img_1
        }
    }
    val image: LiveData<Int> = _image

}