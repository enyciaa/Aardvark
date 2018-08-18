package com.boundless.aardvark.extensions

import android.content.Context
import android.os.Build

fun Context.isLessThanOreo(): Boolean =
    Build.VERSION.SDK_INT < Build.VERSION_CODES.O

fun Context.isOreoOrAbove(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

fun Context.isGreaterThanLollipop(): Boolean =
    Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP

fun Context.isLessThanLollipop(): Boolean =
    Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP

fun Context.isGreaterThanOrEqualToLollipop() =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP

fun Context.isMarshmellowOrAbove(): Boolean =
    Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
