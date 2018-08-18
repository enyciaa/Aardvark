package com.boundless.aardvark.listeners

import android.widget.SeekBar

class SeekBarProgressChangedListener(
    private val onProgressChanged: (progress: Int) -> Unit
) : SeekBar.OnSeekBarChangeListener {

  override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
    onProgressChanged(progress)
  }

  override fun onStartTrackingTouch(seekBar: SeekBar?) {
    // Do nothing
  }

  override fun onStopTrackingTouch(seekBar: SeekBar?) {
    // Do nothing
  }

}