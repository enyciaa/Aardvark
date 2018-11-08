package com.boundless.aardvark.views

import android.content.Context
import androidx.coordinatorlayout.widget.CoordinatorLayout
import android.util.AttributeSet

/**
 * Direct children of this layout can draw behind the status bar if they set the value:
 * fitsSystemWindows = true in xml
 */
class DrawBehindStatusBarLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : androidx.coordinatorlayout.widget.CoordinatorLayout(context, attrs, defStyleAttr) {

  init {
    this.fitsSystemWindows = true
  }
}

