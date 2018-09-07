package com.boundless.aardvark.platform

import android.app.Application
import com.boundless.jerboa.platform.TimeWrapper
import com.jakewharton.threetenabp.AndroidThreeTen
import org.threeten.bp.Instant
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime

open class TimeWrapperImpl(
    application: Application
) : TimeWrapper {

  init {
    AndroidThreeTen.init(application)
  }

  /**
   * month in the form of 1-12
   */
  override fun getTimeMs(year: Int, monthActual: Int, day: Int): Long =
      ZonedDateTime.of(year, monthActual, day, 0, 0, 0, 0, getTimeZone())
          .toInstant()
          .toEpochMilli()

  override fun getCurrentTimeMs(): Long =
      ZonedDateTime.now()
          .toInstant()
          .toEpochMilli()

  override fun isToday(timeMs: Long): Boolean {
    val currentZonedTime = ZonedDateTime.now(getTimeZone())
    val zonedTimeToCheck = getZonedDateTimeFromEpoch(timeMs)

    return areSameDayOfYear(currentZonedTime, zonedTimeToCheck)
  }

  override fun isYesterday(timeMs: Long): Boolean {
    val currentZonedTime = ZonedDateTime.now(getTimeZone())
    val zonedTimeToCheck = getZonedDateTimeFromEpoch(timeMs)

    return (areSameYear(currentZonedTime, zonedTimeToCheck)
        && currentZonedTime.minusDays(1).dayOfYear == zonedTimeToCheck.dayOfYear)
  }

  override fun areTimeOneAndTwoSameDay(timeOne: Long, timeTwo: Long): Boolean {
    val zonedTimeOne = getZonedDateTimeFromEpoch(timeOne)
    val zonedTimeTwo = getZonedDateTimeFromEpoch(timeTwo)

    return (areSameYear(zonedTimeOne, zonedTimeTwo) && areSameDayOfYear(zonedTimeOne, zonedTimeTwo))
  }

  override fun areTimeOneAndTwoConsecutiveDays(timeOne: Long, timeTwo: Long): Boolean {
    val zonedTimeOne = getZonedDateTimeFromEpoch(timeOne)
    val zonedTimeTwo = getZonedDateTimeFromEpoch(timeTwo)

    return (areSameYear(zonedTimeOne, zonedTimeTwo)
        && !areSameDayOfYear(zonedTimeOne, zonedTimeTwo)
        && (zonedTimeOne.plusDays(1).dayOfYear == zonedTimeTwo.dayOfYear
        || zonedTimeOne.minusDays(1).dayOfYear == zonedTimeTwo.dayOfYear))
  }

  override fun getYear(timeMs: Long): Int = getZonedDateTimeFromEpoch(timeMs).year

  /**
   * Returns month value from 0-11
   */
  override fun getMonthOfYear(timeMs: Long): Int = getZonedDateTimeFromEpoch(timeMs).monthValue - 1

  /**
   * Returns month value from 1-12
   */
  override fun getMonthOfYearActual(timeMs: Long): Int = getZonedDateTimeFromEpoch(timeMs).monthValue

  override fun getDayOfYear(timeMs: Long) =
      getZonedDateTimeFromEpoch(timeMs).dayOfYear

  override fun getDayOfMonth(timeMs: Long) =
      getZonedDateTimeFromEpoch(timeMs).dayOfMonth

  protected fun areSameDayOfYear(zonedTimeOne: ZonedDateTime, zonedTimeTwo: ZonedDateTime) =
      zonedTimeOne.dayOfYear == zonedTimeTwo.dayOfYear

  protected fun areSameYear(zonedTimeOne: ZonedDateTime, zonedTimeTwo: ZonedDateTime): Boolean =
      zonedTimeOne.year == zonedTimeTwo.year

  protected fun getZonedDateTimeFromEpoch(epochMs: Long): ZonedDateTime =
      ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMs), getTimeZone())

  protected fun getTimeZone(): ZoneId = ZoneId.systemDefault()
}
