package com.boundless.aardvark.samples.canvas

import android.graphics.Bitmap
import com.boundless.aardvark.canvas.CanvasRectangle
import com.boundless.jerboa.animation.Rectangle
import com.boundless.jerboa.animation.calculateNewCoordinate
import com.boundless.jerboa.animation.randomAngleInRadians

class ExampleCanvasObject(
    bitmap: Bitmap,
    override var bounds: Rectangle
) : CanvasRectangle(bitmap) {

  private val DISTANCE_EACH_FRAME = 1.5

  private var angleOfMovementInRadians: Double = 0.0

  override fun update() {
    angleOfMovementInRadians = pickRandomDirection()

    val newCenterCoordinate = calculateNewCoordinate(bounds.center, angleOfMovementInRadians, DISTANCE_EACH_FRAME)
    bounds = bounds.copy(center = newCenterCoordinate)
  }

  private fun pickRandomDirection(): Double {
    return randomAngleInRadians()
  }
}
