package com.boundless.aardvark.engagement

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.boundless.aardvark.extensions.getColorCompat
import com.boundless.aardvark.extensions.isOreoOrAbove

@RequiresApi(26)
abstract class SystemNotificationChannel(
    private val channelId: String,
    private val channelNameResourceId: Int,
    private val lightColorResourceId: Int,
    private val importance: Int = NotificationManager.IMPORTANCE_LOW,
    private val enableLights: Boolean = true,
    private val enableVibration: Boolean = false
) {

  fun createChannel(context: Context) {
    if (context.isOreoOrAbove())
      getNotificationManagerService(context).createNotificationChannel(buildChannel(context))
  }

  @RequiresApi(Build.VERSION_CODES.O)
  protected open fun buildChannel(context: Context): NotificationChannel {
    return buildBaseChannel(context)
  }

  @RequiresApi(Build.VERSION_CODES.O)
  protected fun buildBaseChannel(context: Context): NotificationChannel {
    val notificationChannel = NotificationChannel(channelId, context.getString(channelNameResourceId), importance)
    notificationChannel.enableLights(enableLights)
    notificationChannel.lightColor = context.getColorCompat(lightColorResourceId)
    notificationChannel.enableVibration(enableVibration)

    return notificationChannel
  }

  private fun getNotificationManagerService(context: Context): NotificationManager {
    return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
  }
}
