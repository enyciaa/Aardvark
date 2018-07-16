package com.boundless.aardvark.adapters

import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.View
import android.view.ViewGroup
import java.util.*

class ViewPageAdapter : PagerAdapter() {

  private val views = ArrayList<View>()

  override fun getItemPosition(`object`: Any): Int {
    val index = views.indexOf(`object`)
    return if (index == -1)
      PagerAdapter.POSITION_NONE
    else
      index
  }

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val v = views[position]
    container.addView(v)
    return v
  }

  override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
    container.removeView(`object` as View)
  }

  override fun getCount(): Int {
    return views.size
  }

  override fun isViewFromObject(view: View, `object`: Any): Boolean {
    return view === `object`
  }

  fun addView(v: View): Int {
    val position = addView(v, views.size)
    notifyDataSetChanged()
    return position
  }

  fun addView(v: View, position: Int): Int {
    views.add(position, v)
    notifyDataSetChanged()
    return position
  }

  fun removeView(pager: ViewPager, v: View): Int {
    val position = removeView(pager, views.indexOf(v))
    notifyDataSetChanged()
    return position
  }

  fun removeView(pager: ViewPager, position: Int): Int {
    pager.adapter = null
    views.removeAt(position)
    pager.adapter = this
    notifyDataSetChanged()
    return position
  }

  fun clearViews() {
    views.clear()
    notifyDataSetChanged()
  }

  fun getView(position: Int): View {
    return views[position]
  }
}
