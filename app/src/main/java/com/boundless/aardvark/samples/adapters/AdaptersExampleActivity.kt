package com.boundless.aardvark.samples.adapters

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.boundless.aardvark.adapters.FragmentPageAdapter
import com.boundless.aardvark.adapters.ViewPageAdapter
import com.boundless.aardvark.samples.R
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