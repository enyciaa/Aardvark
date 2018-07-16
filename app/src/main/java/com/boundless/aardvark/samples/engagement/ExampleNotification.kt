package com.boundless.aardvark.samples.engagement

import android.app.Notification
import android.content.Context
import android.content.Intent
import com.boundless.aardvark.engagement.SystemNotification
import com.boundless.aardvark.samples.R

class ExampleNotification : SystemNotification(
    channelId = "",
    notificationId = 1,
    intent = Intent(Intent.ACTION_AIRPLANE_MODE_CHANGED),
    primaryColorResourceId = R.color.colorPrimary,
    largeIconResourceId = R.drawable.notification_icon_background,
    smallIconResourceId = R.drawable.abc_ratingbar_small_material,
    statusBarTextResourceId = R.string.status_bar_notification_info_overflow,
    titleResourceId = R.string.abc_font_family_title_material,
    contentResourceId = R.string.resource_provider_example_string
) {

  override fun getCategory(): String = Notification.CATEGORY_ALARM

  override fun buildNotification(context: Context): Notification = buildBaseNotification(context).build()
}
