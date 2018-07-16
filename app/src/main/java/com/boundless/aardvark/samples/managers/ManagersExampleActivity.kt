package com.boundless.aardvark.samples.managers

import android.support.v7.app.AppCompatActivity
import com.boundless.aardvark.platform.ToastAnnouncer
import com.boundless.jerboa.platform.Announcer

class ManagersExampleActivity : AppCompatActivity() {

  private val announcer: Announcer = ToastAnnouncer(this)

  override fun onResume() {
    super.onResume()
    announcer.announce("Test toast")
  }
}
