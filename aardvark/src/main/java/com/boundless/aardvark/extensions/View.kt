package com.boundless.aardvark.extensions

import android.app.Activity
import android.content.Context
import android.support.annotation.StyleRes
import android.support.v7.view.ContextThemeWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager

fun View.getLayoutInflater(): LayoutInflater {
  return LayoutInflater.from(context)
}

fun View.getThemedLayoutInflater(activity: Activity, @StyleRes theme: Int): LayoutInflater {
  val contextThemeWrapper = ContextThemeWrapper(activity, theme)
  return getLayoutInflater().cloneInContext(contextThemeWrapper)
}

fun View.setMaxWidth(maxWidth: Int) {
  if (width > maxWidth) {
    val params = layoutParams
    params.width = maxWidth
    layoutParams = params
  }
}

/**
 * Function is invoked when the global layout state or the visibility of views within the view tree changes
 * Listener is immediately unregistered after being invoked
 * So will only ever be called once
 *
 * For example
 * If called from onCreate, the function is invoked once the views become visible to the user
 */
fun View.onViewTreeStateChanged(functionToInvokeOnlyOnce: () -> Unit) {
  if (viewTreeObserver.isAlive) {
    viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
      override fun onGlobalLayout() {
        viewTreeObserver.removeOnGlobalLayoutListener(this)
        functionToInvokeOnlyOnce()
      }
    })
  }
}

fun View.showSoftInput() {
  val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
  imm.showSoftInput(this, 0)
}

/**
 * Ensures system insets are not added to the view.
 *
 * System insets include status bar/navigation bar
 */
fun View.removeWindowInsets() {
  setOnApplyWindowInsetsListener { _, windowInsets -> windowInsets.consumeSystemWindowInsets() }
}

