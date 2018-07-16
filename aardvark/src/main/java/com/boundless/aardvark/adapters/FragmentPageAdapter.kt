package com.boundless.aardvark.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import java.util.*

class FragmentPageAdapter(
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager) {

  private val fragmentList = ArrayList<Fragment>()
  private val fragmentTitleList = ArrayList<String>()

  fun addPage(fragment: Fragment, title: String) {
    fragmentList.add(fragment)
    fragmentTitleList.add(title)
  }

  override fun getItem(position: Int): Fragment {
    return fragmentList[position]
  }

  override fun getCount(): Int {
    return fragmentList.size
  }

  override fun getPageTitle(position: Int): CharSequence {
    return fragmentTitleList[position]
  }

}
