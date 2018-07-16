package com.boundless.aardvark.analytics

import android.app.Activity

interface UiAnalytics {

  fun screen(activity: Activity, screenName: String)

}
