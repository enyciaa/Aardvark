package com.boundless.aardvark.extensions

import android.view.View

fun View.makeVisible() {
  visibility = View.VISIBLE
}

fun View.makeInvisible() {
  visibility = View.INVISIBLE
}

fun View.makeGone() {
  visibility = View.GONE
}