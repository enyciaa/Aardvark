package com.boundless.aardvark.extensions

import android.view.View
import android.view.ViewAnimationUtils
import android.view.animation.AlphaAnimation
import com.boundless.aardvark.listeners.AnimationEndListener

fun View.startFadeOutAnimation(
    animationEndListener: (() -> Unit)? = null,
    duration: Long = 2000,
    startAlpha: Float = 1.0f,
    endAlpha: Float = 0.0f
) {
  val animation = AlphaAnimation(startAlpha, endAlpha)
  animation.duration = duration
  animationEndListener?.let { animation.setAnimationListener(AnimationEndListener(it)) }
  this.startAnimation(animation)
}

fun View.startFadeInAnimation(
    animationEndListener: (() -> Unit)? = null,
    duration: Long = 2000,
    startAlpha: Float = 0.0f,
    endAlpha: Float = 1.0f
) {
  val animation = AlphaAnimation(startAlpha, endAlpha)
  animation.duration = duration
  animationEndListener?.let { animation.setAnimationListener(AnimationEndListener(it))  }
  this.startAnimation(animation)
}

fun View.startCircularRevealFromCenter(
    duration: Long = 2000
) {
  val centerX = this.width / 2
  val centerY = this.height / 2
  val finalRadius = Math.max(this.width, this.height).toFloat()
  val revealAnimator = ViewAnimationUtils.createCircularReveal(this, centerX, centerY, 0f, finalRadius)
  revealAnimator.duration = duration
  revealAnimator.start()
}
