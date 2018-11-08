package com.boundless.aardvark.views

import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import com.boundless.jerboa.ui.Orientation

class TopSpaceDecorator(
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
    if (itemPosition == 0) {
      when (orientation) {
        Orientation.HORIZONTAL -> outRect.left = space
        Orientation.VERTICAL -> outRect.top = space
      }
    }
  }
}