package com.boundless.aardvark.listeners

import android.view.animation.Animation

class AnimationEndListener(private val onAnimationEnd: () -> Unit) : Animation.AnimationListener {

  override fun onAnimationEnd(p0: Animation?) {
    onAnimationEnd()
  }

  override fun onAnimationStart(p0: Animation?) {
    // do nothing
  }

  override fun onAnimationRepeat(p0: Animation?) {
    // do nothing
  }
}
