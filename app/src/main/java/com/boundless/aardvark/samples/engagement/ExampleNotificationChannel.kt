package com.boundless.aardvark.samples.engagement

import android.app.NotificationChannel
import android.content.Context
import android.support.annotation.RequiresApi
import com.boundless.aardvark.engagement.SystemNotificationChannel

@RequiresApi(26)
class ExampleNotificationChannel : SystemNotificationChannel(
    channelId = "",
    channelNameResourceId = 1,
    lightColorResourceId = 1
) {
  override fun buildChannel(context: Context): NotificationChannel {
    return buildBaseChannel(context)
  }
}
