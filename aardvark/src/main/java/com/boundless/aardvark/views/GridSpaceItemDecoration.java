package com.boundless.aardvark.views;

import android.graphics.Rect;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int leftParentSpacing;
    private final int topParentSpacing;
    private final int rightParentSpacing;
    private final int bottomParentSpacing;
    private final int halfItemSpacing;

    public GridSpaceItemDecoration(int leftParentSpacing, int topParentSpacing, int rightParentSpacing, int bottomParentSpacing, int itemSpacing) {
        this.leftParentSpacing = leftParentSpacing;
        this.topParentSpacing = topParentSpacing;
        this.rightParentSpacing = rightParentSpacing;
        this.bottomParentSpacing = bottomParentSpacing;
        halfItemSpacing = itemSpacing / 2;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        int itemPosition = parent.getChildAdapterPosition(view);
        int spanSize = ((GridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanSize();
        int spanIndex = ((GridLayoutManager.LayoutParams) view.getLayoutParams()).getSpanIndex();
        int type = parent.getAdapter().getItemViewType(itemPosition);
        boolean isLastItem = (itemPosition == itemCount - 1);
        boolean isPenultimateItem = (itemPosition == itemCount - 2);

        // Recycler padding
        if (parent.getPaddingLeft() != leftParentSpacing) {
            parent.setPadding(leftParentSpacing, 0, rightParentSpacing, 0);
            parent.setClipToPadding(false);
        }

        // Item padding
        // Need to handle:
        // Whether item in span index one (no left padding)
        // Whether item is in span index two (no right padding)
        if (itemPosition == 0 ||
                (itemPosition == 1 && spanIndex == 1)) {
            // Top row
            outRect.top = topParentSpacing;
            outRect.left = halfItemSpacing;
            outRect.right = halfItemSpacing;
            outRect.bottom = halfItemSpacing;
        } else if ((isPenultimateItem && spanSize == 1 && spanIndex == 0) ||
                isLastItem) {
            // Bottom row
            // Doesn't handle case where last item is 2 spanSize &
            // Penultimate item is 1 spanSize (so is on the row above)
            outRect.top = halfItemSpacing;
            outRect.left = halfItemSpacing;
            outRect.right = halfItemSpacing;
            outRect.bottom = bottomParentSpacing;
        } else {
            outRect.top = halfItemSpacing;
            outRect.left = halfItemSpacing;
            outRect.right = halfItemSpacing;
            outRect.bottom = halfItemSpacing;
        }
    }
}