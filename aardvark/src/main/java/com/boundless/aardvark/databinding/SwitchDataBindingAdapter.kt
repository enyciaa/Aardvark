package com.boundless.aardvark.databinding

import android.content.res.ColorStateList
import androidx.databinding.BindingAdapter
import androidx.annotation.ColorInt
import androidx.core.graphics.drawable.DrawableCompat
import android.widget.Switch

object SwitchDataBindingAdapter {

  @JvmStatic
  @BindingAdapter("switchUncheckedColor", "switchCheckedColor")
  fun switchColor(switch: Switch, @ColorInt defaultColor: Int, @ColorInt checkedColor: Int) {
    val states = arrayOf(
        intArrayOf(-android.R.attr.state_checked),
        intArrayOf(android.R.attr.state_checked)
    )
    val thumbColors = intArrayOf(defaultColor, checkedColor)
    val trackColors = intArrayOf(defaultColor, checkedColor)

    DrawableCompat.setTintList(
        DrawableCompat.wrap(switch.thumbDrawable), ColorStateList(states, thumbColors))
    DrawableCompat.setTintList(
        DrawableCompat.wrap(switch.trackDrawable), ColorStateList(states, trackColors))
  }
}