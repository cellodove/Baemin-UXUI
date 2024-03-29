package com.cellodove.baemin_ux_ui.ui.home

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import com.cellodove.baemin_ux_ui.R


class FadeInLinearlayout : LinearLayout {
    private var context: Context
    private var startanimation: Animation? = null

    constructor(context: Context) : super(context) {
        this.context = context
        initAnimations()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        this.context = context
        initAnimations()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        this.context = context
        initAnimations()
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        this.context = context
        initAnimations()
    }

    private fun initAnimations() {
        startanimation = AnimationUtils.loadAnimation(context, R.anim.anim_button)
    }

    fun show() {
        if (isVisible) return
        show(true)
    }

    fun show(withAnimation: Boolean) {
        if (withAnimation) startAnimation(startanimation)
        this.visibility = VISIBLE
    }
    val isVisible: Boolean
        get() = this.visibility == VISIBLE


    override fun setOnTouchListener(l: OnTouchListener?) {
        super.setOnTouchListener(l)
    }


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
}

