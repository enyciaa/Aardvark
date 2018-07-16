package com.boundless.aardvark.engagement;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * To use:
 * Extend this class for each system alarm
 *
 * User decides how to store alarm ids
 */
public abstract class SystemAlarmHelper {

    public static final String KEY_ALARM_ID = "KEY_ALARM_ID";

    protected AlarmManager alarmManager;
    protected int alarmTimeMinutesThroughDay;

    public SystemAlarmHelper() {
        loadAlarmTime();
    }

    abstract public int getAlarmId();

    abstract public void saveAlarmTime();

    abstract protected void loadAlarmTime();

    public void updateAlarm(int hour, int minute) {
        alarmTimeMinutesThroughDay = convertAlarmTimeToMinutesThroughDay(hour, minute);
        saveAlarmTime();
    }

    public String getDisplayAlarmTime() {
        return String.format(getClockDisplayFormat(), getAlarmHour(), getAlarmMinute());
    }

    public boolean isAlarmSet(Context context) {
        return PendingIntent.getBroadcast(context, getAlarmId(), getBaseIntent(context), PendingIntent.FLAG_NO_CREATE) != null;
    }

    public void cancelAlarm(Context context) {
        if (isAlarmSet(context)) {
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, getAlarmId(), getBaseIntent(context), PendingIntent.FLAG_CANCEL_CURRENT);
            getAlarmManager(context).cancel(pendingIntent);
        }
    }

    public void setAlarm(Context context) {
        getAlarmManager(context).setExact(
                getAlarmType(),
                getAlarmTimeInMillis(),
                buildAlarmIntent(context));
    }

    public void setPriorityAlarm(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getAlarmManager(context).setExactAndAllowWhileIdle(
                    getAlarmType(),
                    getAlarmTimeInMillis(),
                    buildAlarmIntent(context));
        } else {
            getAlarmManager(context).setExact(
                    getAlarmType(),
                    getAlarmTimeInMillis(),
                    buildAlarmIntent(context));
        }
    }

    public void setDailyAlarm(Context context) {
        getAlarmManager(context).setInexactRepeating(
                getAlarmType(),
                getAlarmTimeInMillis(),
                AlarmManager.INTERVAL_DAY,
                buildAlarmIntent(context));
    }

    protected int getAlarmHour() {
        return convertMinutesThroughDayToHours();
    }

    protected int getAlarmMinute() {
        return convertMinutesThroughDayToMinutes();
    }

    protected String getClockDisplayFormat() {
        return "%02d:%02d";
    }

    protected int getAlarmType() {
        return AlarmManager.RTC_WAKEUP;
    }

    protected PendingIntent buildAlarmIntent(Context context) {
        Intent intent = getBaseIntent(context);
        intent.putExtra(KEY_ALARM_ID, getAlarmId());
        return PendingIntent.getBroadcast(context, getAlarmId(), intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @NonNull
    protected Intent getBaseIntent(Context context) {
        return new Intent(context, SystemAlarmReceiver.class);
    }

    protected int convertAlarmTimeToMinutesThroughDay(int hour, int minute) {
        return (int) TimeUnit.HOURS.toMinutes(hour) + minute;
    }

    protected int convertMinutesThroughDayToHours() {
        return (int) TimeUnit.MINUTES.toHours(alarmTimeMinutesThroughDay);
    }

    protected int convertMinutesThroughDayToMinutes() {
        return alarmTimeMinutesThroughDay % 60;
    }

    protected AlarmManager getAlarmManager(Context context) {
        if (alarmManager == null) {
            alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }
        return alarmManager;
    }

    private long getAlarmTimeInMillis() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, getAlarmHour());
        calendar.set(Calendar.MINUTE, getAlarmMinute());
        return checkAlarmOccursInTheFuture(calendar.getTimeInMillis());
    }

    /**
     * Checks the alarm time is after the current time in millis
     *
     * If an alarm is in the past, it would trigger immediately
     */
    private long checkAlarmOccursInTheFuture(long alarmTime) {
        if (alarmTime <= Calendar.getInstance().getTimeInMillis()) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(alarmTime);
            calendar.add(Calendar.DATE, 1);
            alarmTime = calendar.getTimeInMillis();
        }
        return alarmTime;
    }
}
