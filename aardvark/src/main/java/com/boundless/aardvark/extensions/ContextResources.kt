package com.boundless.aardvark.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

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

fun Context.getColorFromAttribute(@AttrRes attr: Int, @ColorRes defaultColorResId: Int): Int {
  val defaultColor = this.getColorCompat(defaultColorResId)
  val typedArray = this.obtainStyledAttributes(intArrayOf(attr))
  try {
    return typedArray.getColor(0, defaultColor)
  } finally {
    typedArray.recycle()
  }
}