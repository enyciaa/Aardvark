package com.boundless.aardvark.extensions

import android.view.View
import android.view.Window
import com.boundless.jerboa.extensions.minusFlag
import com.boundless.jerboa.extensions.withFlag

fun Window.setStatusBarIconColorToDark(isDark: Boolean) {
  if (context.isMarshmellowOrAbove()) {
    val decor = this.decorView
    if (isDark) {
      decor.systemUiVisibility = (decor.systemUiVisibility withFlag View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    } else {
      decor.systemUiVisibility = (decor.systemUiVisibility minusFlag View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
    }
  }
}
