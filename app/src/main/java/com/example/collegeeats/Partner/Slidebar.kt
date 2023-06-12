package com.example.collegeeats.Partner

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.SeekBar


class SlideButton(context: Context?, attrs: AttributeSet?) :
    androidx.appcompat.widget.AppCompatSeekBar(context!!, attrs) {
    private var thumbi: Drawable? = null
    private var listener: SlideButtonListener? = null

    override fun setThumb(thumb: Drawable) {
        super.setThumb(thumb)
        this.thumbi = thumb
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (thumbi!!.bounds.contains(event.x.toInt(), event.y.toInt())) {
                super.onTouchEvent(event)
            } else return false
        } else if (event.action == MotionEvent.ACTION_UP) {
            if (progress > 70) handleSlide()
            progress = 0
        } else super.onTouchEvent(event)
        return true
    }

    private fun handleSlide() {
        listener!!.handleSlide()
    }

    fun setSlideButtonListener(listener: SlideButtonListener?) {
        this.listener = listener
    }
}

interface SlideButtonListener {
    fun handleSlide()
}