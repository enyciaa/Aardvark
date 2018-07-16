package com.boundless.aardvark.samples.providers

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.boundless.aardvark.platform.ResourceProviderImpl
import com.boundless.aardvark.samples.R
import com.boundless.jerboa.platform.ResourceProvider

class ProvidersExampleActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    fetchResources()
  }

  private fun fetchResources() {
    val resourceProvider: ResourceProvider = ResourceProviderImpl(resources)

    val string = resourceProvider.getString(R.string.resource_provider_example_string)
    val stringArray = resourceProvider.getStringArray(R.array.resource_provider_example_array)
  }
}
