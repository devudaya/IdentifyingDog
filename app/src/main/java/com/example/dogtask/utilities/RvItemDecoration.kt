package com.example.dogtask.utilities

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RvItemDecoration(private var displayMode: Int) : RecyclerView.ItemDecoration() {

    companion object {
        const val HORIZONTAL = 0
        const val GRID = 1
    }

    private var mItemOffSet: Int = 8

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        when (displayMode) {
            GRID -> {
                if (parent.layoutManager is GridLayoutManager) {

                    val gridLayoutManager = parent.layoutManager as GridLayoutManager
                    val cols = gridLayoutManager.spanCount
                    val rows: Int = state.itemCount / cols
                    val position = parent.getChildLayoutPosition(view)
                    outRect.left = mItemOffSet
                    outRect.right = if (position % cols == cols - 1) mItemOffSet else 0
                    outRect.top = mItemOffSet
                    outRect.bottom = if (position / cols == rows - 1) mItemOffSet else 0
                }
            }
            HORIZONTAL -> {

                val position = parent.getChildLayoutPosition(view)
                outRect.left = mItemOffSet
                outRect.right = if (position == state.itemCount - 1) mItemOffSet else 0
                outRect.top = mItemOffSet
                outRect.bottom = mItemOffSet
            }
        }
    }
}