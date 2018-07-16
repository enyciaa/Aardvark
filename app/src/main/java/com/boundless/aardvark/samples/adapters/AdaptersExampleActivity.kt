package com.boundless.aardvark.adapters

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.boundless.aardvark.samples.R
import com.boundless.aardvark.samples.adapters.FragmentPage
import com.boundless.aardvark.samples.adapters.ViewPage
import com.boundless.aardvark.samples.databinding.ActivityAdaptersBinding

class AdaptersExampleActivity : AppCompatActivity() {

  private lateinit var binding: ActivityAdaptersBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = DataBindingUtil.setContentView(this, R.layout.activity_adapters)

    initFragmentPagerAdapter()
    initViewPagerAdapter()
  }

  private fun initFragmentPagerAdapter() {
    val adapter = FragmentPageAdapter(supportFragmentManager)
    adapter.addPage(FragmentPage(), "Page")

    binding.viewPager.adapter = adapter
  }

  private fun initViewPagerAdapter() {
    val adapter = ViewPageAdapter()
    adapter.addView(ViewPage(this), 0)

    binding.viewPager.adapter = adapter
  }
}