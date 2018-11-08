package com.boundless.aardvark.samples.analytics

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.boundless.aardvark.analytics.UiAnalytics
import com.boundless.aardvark.samples.R
import com.boundless.jerboa.platform.Analytics

class AnalyticsExampleActivity : AppCompatActivity() {

  private lateinit var analytics: Analytics
  private lateinit var uiAnalytics: UiAnalytics

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_analytics)

    initAnalytics()
    sendAnalyticsEvent()
  }

  private fun initAnalytics() {
    val myAnalytics = MyAnalytics(this)
    analytics = myAnalytics
    uiAnalytics = myAnalytics
  }

  private fun sendAnalyticsEvent() {
    uiAnalytics.screen(this, "Screen Event")

    analytics.event("Event")
    analytics.event("Event", "Big Event Property", "Value")

    analytics.purchase(1.22, "GBP", "item name")

    analytics.exception(Throwable())
    analytics.exception("Non fatal", Thread.currentThread().stackTrace)
  }
}