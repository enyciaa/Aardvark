package com.boundless.aardvark.canvas

import android.graphics.Bitmap
import android.graphics.Canvas
import androidx.annotation.CallSuper
import com.boundless.aardvark.extensions.drawBitmap
import com.boundless.jerboa.animation.Coordinate
import com.boundless.jerboa.animation.Rectangle

abstract class CanvasRectangle(
    private val bitmap: Bitmap
) {

  abstract var bounds: Rectangle

  abstract fun update()

  @CallSuper
  fun draw(canvas: Canvas) {
    canvas.save()
    canvas.drawBitmap(bitmap, bounds.left, bounds.top, bounds.right, bounds.bottom)
    canvas.restore()
  }
}
