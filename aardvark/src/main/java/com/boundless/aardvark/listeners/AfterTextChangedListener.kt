package com.boundless.aardvark.listeners

import android.text.Editable
import android.text.TextWatcher

class AfterTextChangedListener(val afterTextChanged: (text: String) -> Unit) : TextWatcher {

  override fun afterTextChanged(text: Editable?) {
    afterTextChanged(text.toString())
  }

  override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
    // Do nothing
  }

  override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
    // Do nothing
  }
}
