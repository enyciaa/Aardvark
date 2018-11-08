package com.boundless.aardvark.views;

import android.content.Context;
import androidx.viewpager.widget.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Can disable swiping in a view pager
 */
public class NonSwipeViewPager extends ViewPager {

    private boolean pagingEnabled = false;

    public NonSwipeViewPager(Context context) {
        super(context);
    }

    public NonSwipeViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setPagingEnabled(boolean enable) {
        pagingEnabled = enable;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return pagingEnabled;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return pagingEnabled;
    }

}
