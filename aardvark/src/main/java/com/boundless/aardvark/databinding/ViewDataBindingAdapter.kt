package com.boundless.aardvark.databinding

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.databinding.BindingAdapter
import android.graphics.drawable.ColorDrawable
import android.support.annotation.ColorInt
import android.view.View
import com.boundless.aardvark.entities.Visibility
import com.boundless.aardvark.extensions.setMarginEnd
import com.boundless.aardvark.extensions.setMarginStart
import com.boundless.aardvark.extensions.setMaxWidth

object ViewDataBindingAdapter {

  @JvmStatic
  @BindingAdapter("backgroundColorWithAnimation", "backgroundColorWithAnimationDuration")
  fun setBackgroundColor(view: View, @ColorInt newColor: Int, animationDuration: Long) {
    if (view.background == null || view.background !is ColorDrawable) {
      view.setBackgroundColor(newColor)
      return
    }

    val oldColor = (view.background as ColorDrawable).color
    val objectAnimator = ObjectAnimator.ofObject(
        view,
        "backgroundColor",
        ArgbEvaluator(),
        oldColor,
        newColor
    )
    objectAnimator.duration = animationDuration
    objectAnimator.start()
  }

  @JvmStatic
  @BindingAdapter("mappedVisibility")
  fun mappedVisibility(view: View, visibility: Visibility) {
    val newVisibility = when (visibility) {
      Visibility.VISIBLE -> View.VISIBLE
      Visibility.INVISIBLE -> View.INVISIBLE
      Visibility.GONE -> View.GONE
    }

    if (view.visibility != newVisibility)
      view.visibility = newVisibility
  }

  @JvmStatic
  @BindingAdapter("android:layout_marginStart")
  fun setMarginStart(view: View, startMargin: Float) {
    view.setMarginStart(startMargin.toInt())
  }

  @JvmStatic
  @BindingAdapter("android:layout_marginEnd")
  fun setMarginEnd(view: View, endMargin: Float) {
    view.setMarginEnd(endMargin.toInt())
  }

  @JvmStatic
  @BindingAdapter("android:maxWidth")
  fun setMaxWidth(view: View, maxWidth: Float) {
    view.setMaxWidth(maxWidth.toInt())
  }
}
