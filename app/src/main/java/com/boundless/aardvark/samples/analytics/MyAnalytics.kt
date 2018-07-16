package com.boundless.aardvark.samples.analytics

import android.content.Context
import com.boundless.aardvark.analytics.AnalyticsOrchestrator
import com.boundless.aardvark.analytics.FirebaseAnalyticsTracker

class MyAnalytics(context: Context) : AnalyticsOrchestrator() {

  init {
    val firebaseTracker = FirebaseAnalyticsTracker(context)
    firebaseTracker.setUserProperties(userProperties)

    addAnalyticsTracker(firebaseTracker)
  }
}
