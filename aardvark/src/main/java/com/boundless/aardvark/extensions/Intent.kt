package com.boundless.aardvark.extensions

import android.content.Intent

fun Intent.fromExistingActivityIfItExistsInAnyTask(): Intent {
  addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
  addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  return this
}

fun Intent.fromExistingActivityIfItExistsInThisTask(): Intent {
  addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
  return this
}

fun Intent.inExistingTaskIfTaskExists(): Intent {
  addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
  return this
}
