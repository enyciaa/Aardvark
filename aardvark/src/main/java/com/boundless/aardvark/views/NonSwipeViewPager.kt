package com.boundless.aardvark.views

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

/**
 * Can disable swiping in a view pager
 */
class NonSwipeViewPager @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null
) : ViewPager(
        context,
        attrs
) {

    private var pagingEnabled = false

    fun setPagingEnabled(enable: Boolean) {
        pagingEnabled = enable
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        return pagingEnabled
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return pagingEnabled
    }

}
