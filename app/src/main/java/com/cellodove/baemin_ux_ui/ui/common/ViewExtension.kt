package com.cellodove.baemin_ux_ui.ui.common

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View

@SuppressLint("ClickableViewAccessibility")
inline fun <reified T : View> T.setOnTouchAnimationListener(crossinline block: (T) -> Unit){
    setOnTouchListener { v, event ->
        if (event.action == MotionEvent.ACTION_DOWN){
            val scaleDown = ObjectAnimator.ofPropertyValuesHolder(
                v,
                PropertyValuesHolder.ofFloat("scaleX", 0.95f),
                PropertyValuesHolder.ofFloat("scaleY", 0.95f)
            )
            scaleDown.duration = 100
            scaleDown.start()
        }

        if (event.action == MotionEvent.ACTION_UP){
            val scaleUp = ObjectAnimator.ofPropertyValuesHolder(
                v,
                PropertyValuesHolder.ofFloat("scaleX", 1f),
                PropertyValuesHolder.ofFloat("scaleY", 1f)
            )
            scaleUp.duration = 100
            scaleUp.start()
        }

        false
    }
}