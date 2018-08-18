package com.boundless.aardvark.views

import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.view.View
import com.boundless.jerboa.ui.Orientation

class BottomSpaceDecorator(
    private val space: Int,
    private val orientation: Orientation = Orientation.VERTICAL
) : RecyclerView.ItemDecoration() {

  override fun getItemOffsets(
      outRect: Rect,
      view: View,
      parent: RecyclerView,
      state: RecyclerView.State?
  ) {
    val itemPosition = parent.getChildAdapterPosition(view)
    val lastItemPosition = parent.adapter.itemCount - 1
    if (itemPosition == lastItemPosition) {
      when (orientation) {
        Orientation.HORIZONTAL -> outRect.right = space
        Orientation.VERTICAL -> outRect.bottom = space
      }
    }
  }
}