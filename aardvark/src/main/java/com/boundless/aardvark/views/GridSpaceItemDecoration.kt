package com.boundless.aardvark.views

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridSpaceItemDecoration(
        private val leftParentSpacing: Int,
        private val topParentSpacing: Int,
        private val rightParentSpacing: Int,
        private val bottomParentSpacing: Int,
        itemSpacing: Int
) : RecyclerView.ItemDecoration() {

    private val halfItemSpacing: Int = itemSpacing / 2

    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: 0
        val itemPosition = parent.getChildAdapterPosition(view)
        val spanSize = (view.layoutParams as GridLayoutManager.LayoutParams).spanSize
        val spanIndex = (view.layoutParams as GridLayoutManager.LayoutParams).spanIndex
        val isLastItem = itemPosition == itemCount - 1
        val isPenultimateItem = itemPosition == itemCount - 2

        // Recycler padding
        if (parent.paddingLeft != leftParentSpacing) {
            parent.setPadding(leftParentSpacing, 0, rightParentSpacing, 0)
            parent.clipToPadding = false
        }

        // Item padding
        // Need to handle:
        // Whether item in span index one (no left padding)
        // Whether item is in span index two (no right padding)
        if (itemPosition == 0 || itemPosition == 1 && spanIndex == 1) {
            // Top row
            outRect.top = topParentSpacing
            outRect.left = halfItemSpacing
            outRect.right = halfItemSpacing
            outRect.bottom = halfItemSpacing
        } else if (isPenultimateItem && spanSize == 1 && spanIndex == 0 || isLastItem) {
            // Bottom row
            // Doesn't handle case where last item is 2 spanSize &
            // Penultimate item is 1 spanSize (so is on the row above)
            outRect.top = halfItemSpacing
            outRect.left = halfItemSpacing
            outRect.right = halfItemSpacing
            outRect.bottom = bottomParentSpacing
        } else {
            outRect.top = halfItemSpacing
            outRect.left = halfItemSpacing
            outRect.right = halfItemSpacing
            outRect.bottom = halfItemSpacing
        }
    }
}