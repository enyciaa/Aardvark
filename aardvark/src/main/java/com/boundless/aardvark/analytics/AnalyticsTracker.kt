package com.boundless.aardvark.analytics

import android.app.Activity

interface AnalyticsTracker {

  fun screen(activity: Activity, screenName: String)

  fun event(tag: String)

  fun event(tag: String, property: String, propertyValue: String)

  fun event(tag: String, property: String, propertyValue: String, propertyTwo: String, propertyTwoValue: String)

  fun purchase(price: Double, currencyShortCode: String, itemName: String)

  fun setUserProperty(propertyName: String, value: String)

  fun exception(message: String, stackTraceElements: Array<StackTraceElement>)

  fun exception(throwable: Throwable)

}
