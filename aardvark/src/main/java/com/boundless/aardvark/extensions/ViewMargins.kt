package com.boundless.aardvark.extensions

import android.view.View
import android.view.ViewGroup

fun View.setMargin(left: Int, top: Int, right: Int, bottom: Int) {
  val params = layoutParams as ViewGroup.MarginLayoutParams
  params.setMargins(left, top, right, bottom)
  layoutParams = params
}

fun View.setMarginRight(margin: Int) {
  val params = layoutParams as ViewGroup.MarginLayoutParams
  params.setMargins(params.leftMargin, params.topMargin, margin, params.bottomMargin)
  layoutParams = params
}

fun View.setMarginEnd(margin: Int) {
  val params = layoutParams as ViewGroup.MarginLayoutParams
  params.marginEnd = margin
  layoutParams = params
}

fun View.setMarginTop(margin: Int) {
  val params = layoutParams as ViewGroup.MarginLayoutParams
  params.setMargins(params.leftMargin, margin, params.rightMargin, params.bottomMargin)
  layoutParams = params
}

fun View.setMarginStart(margin: Int) {
  val params = layoutParams as ViewGroup.MarginLayoutParams
  params.marginStart = margin
  layoutParams = params
}

fun View.setMarginLeft(margin: Int) {
  val params = layoutParams as ViewGroup.MarginLayoutParams
  params.setMargins(margin, params.topMargin, params.rightMargin, params.bottomMargin)
  layoutParams = params
}

fun View.setMarginBottom(margin: Int) {
  val params = layoutParams as ViewGroup.MarginLayoutParams
  params.setMargins(params.leftMargin, params.topMargin, params.rightMargin, margin)
  layoutParams = params
}