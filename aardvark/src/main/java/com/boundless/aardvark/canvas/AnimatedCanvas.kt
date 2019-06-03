package com.boundless.aardvark.canvas

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.os.Handler
import android.os.Looper
import androidx.annotation.CallSuper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.boundless.jerboa.animation.Coordinate
import com.boundless.jerboa.animation.Rectangle
import com.boundless.jerboa.animation.randomNumberInRange
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * See https://github.com/danielzeller/Depth-LIB-Android-/blob/master/app/src/main/java/no/agens/depth/BearSceneView.java
 * For how to do cool animations
 */
abstract class AnimatedCanvas @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

  protected open val FRAME_RATE = 60

  private val mainThreadHandler by lazy { Handler(Looper.getMainLooper()) }
  private var isInitialised: Boolean = false
  private val timer: Timer = Timer()

  @CallSuper
  override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
    super.onLayout(changed, left, top, right, bottom)
    if (!isInitialised) {
      initCanvas()
      isInitialised = true
    }

    startUpdateLoop()
  }

  protected abstract fun initCanvas()

  protected fun generateRandomCoordinateInCanvasBounds(widthOfSprite: Double, heightOfSprite: Double): Coordinate {
    val centerX = randomNumberInRange(0 + widthOfSprite, this.width - widthOfSprite)
    val centerY = randomNumberInRange(0 + heightOfSprite, this.height - heightOfSprite)
    return Coordinate(centerX, centerY)
  }

  private fun startUpdateLoop() {
    val interval = TimeUnit.SECONDS.toMillis(1) / FRAME_RATE
    timer.scheduleAtFixedRate(UpdateLoopTask(), 0, interval)
  }

  inner class UpdateLoopTask : TimerTask() {
    override fun run() {
      mainThreadHandler.post {
        triggerRedraw()
        update()
      }
    }
  }

  protected abstract fun update()

  @CallSuper
  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)
    drawCanvas(canvas)
  }

  protected abstract fun drawCanvas(canvas: Canvas)

  protected fun triggerRedraw() {
    invalidate()
  }

  protected fun isInBounds(rectangle: Rectangle): Boolean {
    if (rectangle.left > this.left &&
        rectangle.right < this.right &&
        rectangle.top > this.top &&
        rectangle.bottom < this.bottom
    ) {
      return true
    }

    return false
  }

  @CallSuper
  @SuppressLint("ClickableViewAccessibility")
  override fun onTouchEvent(event: MotionEvent): Boolean {
    return when (event.action) {
      MotionEvent.ACTION_DOWN -> {
        val x = event.x.toDouble()
        val y = event.y.toDouble()
        touchReceivedAt(Coordinate(x, y))
        true
      }
      else -> false
    }
  }

  @CallSuper
  protected open fun touchReceivedAt(coordinate: Coordinate) {

  }
}
