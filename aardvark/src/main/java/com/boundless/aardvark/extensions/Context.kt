package com.boundless.aardvark.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.annotation.ColorRes
import android.support.annotation.DimenRes
import android.support.annotation.DrawableRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.inputmethod.InputMethodManager

fun Context.getColorCompat(@ColorRes colorResId: Int): Int {
  return ContextCompat.getColor(this, colorResId)
}

fun Context.getIntDimension(@DimenRes dimenId: Int): Int {
  return resources.getDimensionPixelOffset(dimenId)
}

fun Context.getDimension(@DimenRes dimenId: Int): Float {
  return resources.getDimension(dimenId)
}

@SuppressLint("NewApi")
fun Context.getDrawableCompat(@DrawableRes id: Int): Drawable {
  return if (isLessThanLollipop()) {
    this.resources.getDrawable(id)
  } else {
    this.resources.getDrawable(id, null)
  }
}

fun Context.getBitmap(@DrawableRes resId: Int): Bitmap {
  val drawable = getDrawable(resId)
  val bitmap = Bitmap.createBitmap(
      drawable.intrinsicWidth,
      drawable.intrinsicHeight,
      Bitmap.Config.ARGB_8888
  )
  val canvas = Canvas(bitmap)
  drawable.setBounds(0, 0, canvas.width, canvas.height)
  drawable.draw(canvas)
  return bitmap
}

fun Context.getTintedDrawable(@DrawableRes drawableResId: Int, @ColorRes colorResId: Int): Drawable {
  val drawable = this.getDrawableCompat(drawableResId)
  drawable.setColorFilter(this.getColorCompat(colorResId), PorterDuff.Mode.SRC_IN)
  return drawable
}

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

fun Context.isLessThanOreo(): Boolean =
    Build.VERSION.SDK_INT < Build.VERSION_CODES.O

fun Context.isOreoOrAbove(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun Context.isGreaterThanLollipop(): Boolean =
    Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP

fun Context.isLessThanLollipop(): Boolean =
    Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP

fun Context.isGreaterThanOrEqualToLollipop() =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun Context.isMarshmellowOrAbove(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

fun Context.hideSoftInput() {
  val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  val focusedView = (this as AppCompatActivity).currentFocus

  if (focusedView != null)
    imm.hideSoftInputFromWindow(focusedView.windowToken, 0)
}
