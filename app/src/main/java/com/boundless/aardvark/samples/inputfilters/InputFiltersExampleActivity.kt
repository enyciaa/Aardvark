package com.boundless.aardvark.samples.inputfilters

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputFilter
import com.boundless.aardvark.inputfilters.CharacterInputFilter
import com.boundless.aardvark.samples.R
import com.boundless.aardvark.samples.databinding.ActivityInputfiltersBinding

class InputFiltersExampleActivity : AppCompatActivity() {

  private lateinit var binding: ActivityInputfiltersBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_inputfilters)

    setAllowedCharacters("abcdefghijklmnopqrstuvwxyz")
  }

  fun setAllowedCharacters(allowedCharacters: String) {
    val currentInputFilters = binding.editText.filters
    val newInputFilters = arrayOf<InputFilter>(CharacterInputFilter(allowedCharacters))

    currentInputFilters.forEach {
      if (it !is CharacterInputFilter)
        newInputFilters + it
    }

    binding.editText.filters = newInputFilters
  }
}