package com.boundless.aardvark.extensions

import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.annotation.ColorInt

fun Drawable.setColor(@ColorInt color: Int) {
  mutate().setColorFilter(color, PorterDuff.Mode.SRC_IN)
}
