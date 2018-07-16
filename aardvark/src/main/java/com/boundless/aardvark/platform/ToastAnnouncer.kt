package com.boundless.aardvark.platform

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.boundless.aardvark.R
import com.boundless.aardvark.extensions.getColorCompat
import com.boundless.aardvark.extensions.getDrawableCompat
import com.boundless.aardvark.extensions.isGreaterThanOrEqualToLollipop
import com.boundless.jerboa.platform.Announcer

open class ToastAnnouncer(
    private val context: Context
) : Announcer {

  private var toast: Toast? = null
  private var textColor: Int? = null
  private var backgroundColor: Int? = null
  private var horizontalPadding: Int? = null

  override fun announce(text: String) {
    cancelAnnouncement()
    toast = Toast.makeText(context, text, Toast.LENGTH_SHORT)
    styleToast()
    toast?.show()
  }

  override fun announceLong(text: String) {
    cancelAnnouncement()
    toast = Toast.makeText(context, text, Toast.LENGTH_LONG)
    styleToast()
    toast?.show()
  }

  override fun cancelAnnouncement() {
    if (toast != null)
      toast?.cancel()
    toast = null
  }

  private fun styleToast() {
    if (toast != null) {
      fetchStyle()
      styleBackground()
      styleTextView()
    }
  }

  private fun fetchStyle() {
    if (textColor != null && backgroundColor != null && horizontalPadding != null)
      return

    val ta = context.obtainStyledAttributes(R.style.ToastStyle, R.styleable.ToastStyle)
    textColor = ta.getColor(
        R.styleable.ToastStyle_android_textColor,
        context.getColorCompat(android.R.color.black))
    horizontalPadding = ta.getDimension(
        R.styleable.ToastStyle_android_padding,
        0f).toInt()
    backgroundColor = ta.getColor(
        R.styleable.ToastStyle_android_background,
        context.getColorCompat(android.R.color.black))
    ta.recycle()
  }

  private fun styleBackground() {
    val view = toast?.view
    val backgroundDrawable = context.getDrawableCompat(R.drawable.toast_background)
    if (context.isGreaterThanOrEqualToLollipop()) {
      backgroundColor?.let { backgroundDrawable.setTint(it) }
    }
    view?.background = backgroundDrawable
    toast?.view = view
  }

  private fun styleTextView() {
    val textView = toast?.view?.findViewById<View>(android.R.id.message) as TextView
    horizontalPadding?.let { textView.setPadding(it, 0, it, 0) }
    textColor?.let { textView.setTextColor(it) }
    textView.gravity = Gravity.CENTER
  }
}
