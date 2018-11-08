package com.boundless.aardvark.extensions

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

fun Context.isLandscape(): Boolean {
  return resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
}

fun Context.isNotLandscape(): Boolean {
  return !isLandscape()
}

fun Context.getNavBarHeight(): Int {
  val navBarHeightResId = if (isLandscape())
    "navigation_bar_height_landscape"
  else
    "navigation_bar_height"

  val id = resources.getIdentifier(navBarHeightResId, "dimen", "android")
  if (id > 0)
    return resources.getDimensionPixelSize(id)
  else
    return 0
}

fun Context.hideSoftInput() {
  val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  val focusedView = (this as AppCompatActivity).currentFocus

  if (focusedView != null)
    imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
}
