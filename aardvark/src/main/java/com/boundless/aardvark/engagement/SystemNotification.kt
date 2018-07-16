package com.boundless.aardvark.engagement

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import com.boundless.aardvark.extensions.getBitmap
import com.boundless.aardvark.extensions.getColorCompat
import com.boundless.aardvark.extensions.isMarshmellowOrAbove
import com.boundless.aardvark.extensions.isOreoOrAbove

abstract class SystemNotification(
    private val channelId: String,
    private val notificationId: Int,
    private val intent: Intent,
    private val primaryColorResourceId: Int,
    private val largeIconResourceId: Int,
    private val smallIconResourceId: Int,
    private val statusBarTextResourceId: Int,
    private val titleResourceId: Int,
    private val contentResourceId: Int,
    private val visibility: Int = Notification.VISIBILITY_PRIVATE,
    private val isAutoCancel: Boolean = true,
    private val group: String = "DEFAULT",
    private val pendingIntentType: Int = PendingIntent.FLAG_UPDATE_CURRENT
) {

  fun displayNotification(context: Context) {
    getNotificationManagerService(context).notify(notificationId, buildNotification(context))
  }

  @RequiresApi(23)
  protected open fun getCategory() = Notification.CATEGORY_REMINDER

  protected open fun buildNotification(context: Context): Notification {
    return buildBaseNotification(context).build()
  }

  protected fun buildBaseNotification(context: Context): NotificationCompat.Builder {
    val builder = if (context.isOreoOrAbove())
      NotificationCompat.Builder(context, channelId)
    else
      NotificationCompat.Builder(context)

    builder.apply {
      setContentIntent(getPendingIntent(context))
      setWhen(System.currentTimeMillis())
      setAutoCancel(isAutoCancel)
      setGroup(group)
      setVisibility(visibility)
      setSmallIcon(smallIconResourceId)
      setColor(context.getColorCompat(primaryColorResourceId))
      setTicker(context.getString(statusBarTextResourceId))
      setLargeIcon(context.getBitmap(largeIconResourceId))
      setContentTitle(context.getString(titleResourceId))
      setContentText(context.getString(contentResourceId))
    }

    if (context.isMarshmellowOrAbove()) {
      builder.setCategory(getCategory())
    }

    return builder
  }

  private fun getPendingIntent(context: Context): PendingIntent {
    return PendingIntent.getActivity(context, notificationId, intent, pendingIntentType)
  }

  private fun getNotificationManagerService(context: Context): NotificationManager {
    return context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
  }
}
