package com.boundless.aardvark.engagement

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * To use:
 * Extend this class and fill handleAlarm method
 *
 * User decides how to store alarm ids
 */
abstract class SystemAlarmReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {
    val bundle = intent.extras
    handleAlarm(bundle.getInt(SystemAlarmHelper.KEY_ALARM_ID))
  }

  abstract fun handleAlarm(id: Int)

}