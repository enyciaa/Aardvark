package com.boundless.aardvark.extensions

import android.text.InputType
import android.text.method.DigitsKeyListener
import android.widget.EditText
import com.boundless.jerboa.extensions.addFlag
import java.util.*

fun EditText.allowOnlyPositiveIntegersCompat() {
  inputType = inputType addFlag InputType.TYPE_CLASS_NUMBER

  keyListener = if (context.isLessThanOreo())
    DigitsKeyListener.getInstance(false, false)
  else
    DigitsKeyListener.getInstance(Locale.getDefault(), false, false)
}

fun EditText.setNoSuggestions() {
  inputType = inputType addFlag InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
}
