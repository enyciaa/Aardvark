package com.boundless.aardvark.samples.engagement

import com.boundless.aardvark.engagement.SystemAlarmHelper

class AlarmExample : SystemAlarmHelper() {

  override fun getAlarmId(): Int {
    return 101
  }

  override fun saveAlarmTime() {
    // save alarmTimeMinutesThroughDay
  }

  override fun loadAlarmTime() {
    alarmTimeMinutesThroughDay = 1098 // fetched from database
  }
}
