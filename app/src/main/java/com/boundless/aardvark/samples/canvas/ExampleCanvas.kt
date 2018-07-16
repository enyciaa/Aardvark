package com.boundless.aardvark.samples.canvas

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import com.boundless.aardvark.canvas.AnimatedCanvas
import com.boundless.aardvark.extensions.getBitmap
import com.boundless.aardvark.samples.R
import com.boundless.jerboa.animation.Coordinate
import com.boundless.jerboa.animation.Rectangle

/**
 * See, for how to do cool animations
 * https://github.com/danielzeller/Depth-LIB-Android-/blob/master/app/src/main/java/no/agens/depth/BearSceneView.java
 */
class ExampleCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AnimatedCanvas(context, attrs, defStyleAttr) {

  override val FRAME_RATE: Int = 50

  private var balloons: List<ExampleCanvasObject> = listOf()
  private var balloonClickCallback: (() -> Unit)? = null

  override fun initCanvas() {
    balloons = (1..26).map {
      val width = 0.22 * this.width
      val height = 0.25 * this.height

      ExampleCanvasObject(
          bitmap = context.getBitmap(R.drawable.canvas_object),
          bounds = Rectangle(width, height, generateRandomCoordinateInCanvasBounds(width, height))
      )
    }
  }

  override fun drawCanvas(canvas: Canvas) {
    balloons.forEach { it.draw(canvas) }
  }

  override fun update() {
    balloons.forEach { it.update() }
  }

  override fun touchReceivedAt(coordinate: Coordinate) {
    super.touchReceivedAt(coordinate)
    balloonClickCallback?.invoke()
  }

  fun setAllBalloonsClickedCallback(callback: () -> Unit) {
    this.balloonClickCallback = callback
  }
}
