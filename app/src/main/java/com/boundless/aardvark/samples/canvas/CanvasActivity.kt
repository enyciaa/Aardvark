package com.boundless.aardvark.samples.canvas

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.boundless.aardvark.samples.R
import com.boundless.aardvark.samples.databinding.ActivityCanvasBinding

class CanvasActivity : AppCompatActivity() {

  private lateinit var binding: ActivityCanvasBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_canvas)
    attachListeners()
  }

  private fun attachListeners() {
    binding.canvas.setAllBalloonsClickedCallback { /*Do stuff*/ }
  }
}
