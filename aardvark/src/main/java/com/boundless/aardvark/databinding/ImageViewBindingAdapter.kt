package com.boundless.aardvark.databinding

import androidx.databinding.BindingAdapter
import android.widget.ImageView

object ImageViewBindingAdapter {

  @JvmStatic
  @BindingAdapter("srcResource")
  fun setImageResource(imageView: ImageView, srcResource: Int) {
    imageView.setImageResource(srcResource)
  }

}
