package com.boundless.aardvark.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

/**
 * A relative Layout on which can deactivate touch events.
 */
public class TouchDisablableFrameLayout extends FrameLayout {

    private boolean touchEnabled = true;

    public TouchDisablableFrameLayout(Context context) {
        super(context);
    }

    public TouchDisablableFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchDisablableFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TouchDisablableFrameLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void enableTouch() {
        touchEnabled = true;
    }

    public void disableTouch() {
        touchEnabled = false;
    }

    public boolean isTouchEnabled() {
        return touchEnabled;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return !touchEnabled;
    }
}
