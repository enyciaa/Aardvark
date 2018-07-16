package com.boundless.aardvark.analytics

class FirebaseTagCorrector() {

  /**
   * For format of tag see:
   * https://firebase.google.com/docs/reference/android/com/google/firebase/analytics/FirebaseAnalytics#logEvent(java.lang.String,%20android.os.Bundle)
   */
  fun transformTagForFirebase(tag: String): String {
    val allowedCharacters = Regex("[^A-Za-z_]")

    return tag
        .replace(" ", "_")
        .replace(allowedCharacters, "")
        .toLowerCase()
  }
}
