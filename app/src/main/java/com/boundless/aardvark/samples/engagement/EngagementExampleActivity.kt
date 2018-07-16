package com.boundless.aardvark.samples.engagement

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.boundless.aardvark.extensions.isOreoOrAbove
import com.boundless.aardvark.samples.R

class EngagementExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_analytics)

    setSystemAlarm()
    createNotificationChannels()
    displayNotification()
  }

  private fun createNotificationChannels() {
    if (isOreoOrAbove())
      ExampleNotificationChannel().createChannel(this)
  }

  private fun displayNotification() {
    val notificationExample = ExampleNotification()
    notificationExample.displayNotification(this)
  }

  private fun setSystemAlarm() {
    val alarmExample = AlarmExample()
    alarmExample.setAlarm(this)
    alarmExample.cancelAlarm(this)
  }
}
