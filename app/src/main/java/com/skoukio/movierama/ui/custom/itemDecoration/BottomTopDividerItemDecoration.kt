package com.skoukio.movierama.ui.custom.itemDecoration

import android.content.Context
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.skoukio.movierama.R

class BottomTopDividerItemDecoration (
    context: Context,
    orientation: Int,
    private val topMargin: Int,
    private val BottomMargin: Int,
    private val betweenMargin: Int = 0
) : DividerItemDecoration(context, orientation) {

    init {
        setDrawable(ColorDrawable(ContextCompat.getColor(context, R.color.transparent)))
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val viewPosition = parent.getChildAdapterPosition(view)
        when {
            viewPosition == 0 -> outRect.set(0, topMargin, 0, betweenMargin)
            viewPosition + 1 == parent.adapter?.itemCount -> outRect.set(
                0,
                betweenMargin,
                0,
                BottomMargin
            )
            else -> outRect.set(0, betweenMargin, 0, betweenMargin)
        }
    }
}