package com.boundless.aardvark.analytics

import org.amshove.kluent.shouldEqual
import org.junit.Test

class FirebaseTagCorrectorTest {

  private val firebaseTagCorrector = FirebaseTagCorrector()

  @Test
  fun testTagsTransformedCorrectly() {
    val incorrectToCorrectTags = mapOf(
        "name with spaces" to "name_with_spaces",
        "@%$^&*!/tag" to "tag",
        "UPPERCASE" to "uppercase"
    )

    incorrectToCorrectTags.forEach {
      val result = firebaseTagCorrector.transformTagForFirebase(it.key)
      result shouldEqual it.value
    }
  }
}
