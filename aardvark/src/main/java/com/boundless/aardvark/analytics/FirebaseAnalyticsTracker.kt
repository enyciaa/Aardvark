package com.boundless.aardvark.analytics

import android.app.Activity
import android.content.Context
import com.boundless.aardvark.NonFatalException
import com.crashlytics.android.Crashlytics
import com.google.firebase.analytics.FirebaseAnalytics
import android.os.Bundle
import java.util.concurrent.TimeUnit

/**
 * Events usually take 4-6 hours to show up in firebase dashboard
 *
 * Require manifest permissions:
 * [android.permission.INTERNET]
 * [android.permission.ACCESS_NETWORK_STATE]
 * [android.permission.WAKE_LOCK]
 */
class FirebaseAnalyticsTracker(context: Context) : AnalyticsTracker {

  private val MIN_SESSION_DURATION_MS: Long = TimeUnit.SECONDS.toMillis(5)
  private val MAX_SESSION_INACTIVITY_MS: Long = TimeUnit.MINUTES.toMillis(10)

  private val firebaseAnalytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)
  private val firebaseTagCorrector: FirebaseTagCorrector = FirebaseTagCorrector()

  init {
    firebaseAnalytics.setAnalyticsCollectionEnabled(true) // Can disable this to stop collection of data
    firebaseAnalytics.setMinimumSessionDuration(MIN_SESSION_DURATION_MS)
    firebaseAnalytics.setSessionTimeoutDuration(MAX_SESSION_INACTIVITY_MS)
  }

  fun setUserProperties(userProperties: Map<String, String>) {
    userProperties.forEach {
      firebaseAnalytics.setUserProperty(it.key, it.value)
    }
  }

  override fun screen(activity: Activity, screenName: String) {
    firebaseAnalytics.setCurrentScreen(activity, screenName, null)
    Crashlytics.log(1, "Screen", screenName)
  }

  override fun event(tag: String) {
    firebaseAnalytics.logEvent(transformTagForFirebase(tag), null)
    Crashlytics.log(1, "Event", tag)
  }

  override fun event(tag: String, property: String, propertyValue: String) {
    val params = Bundle()
    params.putString(transformTagForFirebase(property), propertyValue)
    firebaseAnalytics.logEvent(transformTagForFirebase(tag), params)
    Crashlytics.log(1, "Event", tag)
  }

  override fun event(tag: String, property: String, propertyValue: String, propertyTwo: String, propertyTwoValue: String) {
    val params = Bundle()
    params.putString(transformTagForFirebase(property), propertyValue)
    params.putString(transformTagForFirebase(propertyTwo), propertyTwoValue)
    firebaseAnalytics.logEvent(transformTagForFirebase(tag), params)
    Crashlytics.log(1, "Event", tag)
  }

  override fun purchase(price: Double, currencyShortCode: String, itemName: String) {
    val params = Bundle()
    params.putString(transformTagForFirebase("item_name"), itemName)
    params.putString(transformTagForFirebase("price"), price.toString())
    params.putString(transformTagForFirebase("currency"), currencyShortCode)
    firebaseAnalytics.logEvent(transformTagForFirebase("purchase"), params)
    Crashlytics.log(1, "Event", "Purchase")
  }

  override fun setUserProperty(propertyName: String, value: String) {
    firebaseAnalytics.setUserProperty(propertyName, value)
  }

  override fun exception(message: String, stackTraceElements: Array<StackTraceElement>) {
    val exception = NonFatalException(message, stackTraceElements)
    Crashlytics.logException(exception)
  }

  override fun exception(throwable: Throwable) {
    Crashlytics.logException(throwable)
  }

  /**
   * For format of tag see:
   * https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics#logEvent(java.lang.String,%20android.os.Bundle)
   */
  private fun transformTagForFirebase(tag: String): String {
    return firebaseTagCorrector.transformTagForFirebase(tag)
  }
}
