package com.boundless.aardvark.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.util.*

class FragmentPageAdapter(
    fragmentManager: androidx.fragment.app.FragmentManager
) : androidx.fragment.app.FragmentPagerAdapter(fragmentManager) {

  private val fragmentList = ArrayList<androidx.fragment.app.Fragment>()
  private val fragmentTitleList = ArrayList<String>()

  fun addPage(fragment: androidx.fragment.app.Fragment, title: String) {
    fragmentList.add(fragment)
    fragmentTitleList.add(title)
  }

  override fun getItem(position: Int): androidx.fragment.app.Fragment {
    return fragmentList[position]
  }

  override fun getCount(): Int {
    return fragmentList.size
  }

  override fun getPageTitle(position: Int): CharSequence {
    return fragmentTitleList[position]
  }

}
