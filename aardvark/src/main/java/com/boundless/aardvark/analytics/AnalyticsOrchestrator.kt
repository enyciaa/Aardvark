package com.boundless.aardvark.analytics

import android.app.Activity
import com.boundless.jerboa.platform.Analytics
import java.util.*

/**
 * To use:
 * Extend this class
 * Initialise trackers in constructor
 * Must be done in constructor as trackers may need different initial variables
 * This can be decided in users implementation
 *
 * Can use default trackers from this library
 * [FirebaseAnalyticsTracker]
 * Or can use your own tracker by implementing [AnalyticsTracker]
 */
abstract class AnalyticsOrchestrator : UiAnalytics, Analytics {

  private var analyticsTrackers: MutableList<AnalyticsTracker> = ArrayList()

  override fun screen(activity: Activity, screenName: String) {
    checkAtLeastOneTrackerExists()
    for (tracker in analyticsTrackers) {
      tracker.screen(activity, screenName)
    }
  }

  override fun event(tag: String) {
    checkAtLeastOneTrackerExists()
    for (tracker in analyticsTrackers) {
      tracker.event(tag)
    }
  }

  override fun event(tag: String, property: String, propertyValue: String) {
    checkAtLeastOneTrackerExists()
    for (tracker in analyticsTrackers) {
      tracker.event(tag, property, propertyValue)
    }
  }

  override fun event(tag: String, property: String, propertyValue: String, propertyTwo: String, propertyTwoValue: String) {
    checkAtLeastOneTrackerExists()
    for (tracker in analyticsTrackers) {
      tracker.event(tag, property, propertyValue, propertyTwo, propertyTwoValue)
    }
  }

  override fun purchase(price: Double, currencyShortCode: String, itemName: String) {
    checkAtLeastOneTrackerExists()
    for (tracker in analyticsTrackers) {
      tracker.purchase(price, currencyShortCode, itemName)
    }
  }

  override fun exception(message: String, stackTraceElements: Array<StackTraceElement>) {
    checkAtLeastOneTrackerExists()
    for (tracker in analyticsTrackers) {
      tracker.exception(message, stackTraceElements)
    }
  }

  override fun exception(throwable: Throwable) {
    checkAtLeastOneTrackerExists()
    for (tracker in analyticsTrackers) {
      tracker.exception(throwable)
    }
  }

  fun setAnalyticsTrackers(analyticsTrackers: MutableList<AnalyticsTracker>) {
    this.analyticsTrackers = analyticsTrackers
  }

  fun addAnalyticsTracker(analyticsTracker: AnalyticsTracker) {
    analyticsTrackers.add(analyticsTracker)
  }

  val trackerCount: Int
    get() = analyticsTrackers.size

  protected fun checkAtLeastOneTrackerExists() {
    if (trackerCount == 0) {
      throw RuntimeException("No trackers found")
    }
  }
}
