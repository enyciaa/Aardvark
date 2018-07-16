package com.boundless.aardvark.platform

import android.content.res.Resources
import android.support.annotation.StringRes
import com.boundless.jerboa.platform.ResourceProvider

open class ResourceProviderImpl(
    private val resources: Resources
) : ResourceProvider {

  override fun getString(@StringRes stringRes: Int): String = resources.getString(stringRes)

  override fun getStringArray(@StringRes stringArrayRes: Int): List<String> = resources.getStringArray(stringArrayRes).toList()
}
