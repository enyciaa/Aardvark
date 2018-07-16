package com.boundless.aardvark.extensions

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF

fun Canvas.drawBitmap(
    bitmap: Bitmap,
    left: Double,
    top: Double,
    right: Double,
    bottom: Double
) {
  val rectangle = RectF(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat())
  this.drawBitmap(bitmap, null, rectangle, null)
}
