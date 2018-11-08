package com.boundless.aardvark.views

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.boundless.jerboa.ui.Orientation

/**
 * Will add spacing to the bottom of every item.
 * Apart from the last two items.
 */
class ItemSpacingDecoration(
    private val space: Int,
    private val orientation: Orientation = Orientation.VERTICAL
) : RecyclerView.ItemDecoration() {

  override fun getItemOffsets(
          outRect: Rect,
          view: View,
          parent: RecyclerView,
          state: RecyclerView.State
  ) {
    val itemPosition = parent.getChildAdapterPosition(view)
    if (itemPosition < parent.adapter!!.itemCount - 1) {
      when (orientation) {
        Orientation.HORIZONTAL -> outRect.right = space
        Orientation.VERTICAL -> outRect.bottom = space
      }
    }
  }
}