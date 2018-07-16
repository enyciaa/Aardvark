package com.boundless.aardvark.extensions

import android.support.v4.app.Fragment

val Fragment.contextForced
    get() = context!!
