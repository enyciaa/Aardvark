package com.boundless.aardvark.inputfilters

import android.text.InputFilter
import android.text.SpannableString
import android.text.Spanned
import android.text.TextUtils

/**
 * Pass A string of allowed characters in the constructor
 * i.e. "ABCabc123"
 */
class CharacterInputFilter(
    private val allowedCharacters: String
) : InputFilter {

  override fun filter(
      source: CharSequence,
      start: Int,
      end: Int,
      dest: Spanned,
      dstart: Int,
      dend: Int
  ): CharSequence? {
    var keepOriginal = true
    val sb = StringBuilder(end - start)
    (start until end)
        .map { source[it] }
        .forEach { if (isCharAllowed(it)) sb.append(it) else keepOriginal = false }

    return if (keepOriginal)
      null
    else {
      if (source is Spanned) {
        val sp = SpannableString(sb)
        TextUtils.copySpansFrom(source, start, sb.length, null, sp, 0)
        sp
      } else {
        sb
      }
    }
  }

  private fun isCharAllowed(character: Char): Boolean {
    return allowedCharacters.contains(character, false)
  }
}