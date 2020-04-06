package com.lockwood.core.extensions

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

val RecyclerView.LayoutManager.firstVisibleItemPosition: Int
    get() {
        return when (this) {
            is LinearLayoutManager -> {
                findFirstCompletelyVisibleItemPosition()
            }
            is GridLayoutManager -> {
                findFirstCompletelyVisibleItemPosition()
            }
            else -> {
                throw IllegalStateException("Unknown LayoutManager type $this")
            }
        }
    }

val RecyclerView.LayoutManager.lastVisibleItemPosition: Int
    get() {
        return when (this) {
            is LinearLayoutManager -> {
                findLastCompletelyVisibleItemPosition()
            }
            is GridLayoutManager -> {
                findLastCompletelyVisibleItemPosition()
            }
            else -> {
                throw IllegalStateException("Unknown LayoutManager type $this")
            }
        }
    }


val RecyclerView.isLastItemVisible: Boolean
    get() {
        val layoutManager = layoutManager
        checkNotNull(layoutManager)
        return layoutManager.lastVisibleItemPosition == layoutManager.itemCount - 1
    }

val RecyclerView.isFirstItemVisible: Boolean
    get() {
        val layoutManager = layoutManager
        checkNotNull(layoutManager)
        return layoutManager.firstVisibleItemPosition == 0
    }

fun RecyclerView.LayoutManager.isLastItemVisible(offset: Int): Boolean {
    if (this is GridLayoutManager) {
        val lastItemPosition = lastVisibleItemPosition
        val spanCount = spanCount
        val size = itemCount - (1 / spanCount) - offset / spanCount
        if (lastItemPosition >= size) {
            return true
        }
    } else if (this is LinearLayoutManager) {
        val lastItemPosition = lastVisibleItemPosition
        val size = itemCount - offset
        if (lastItemPosition >= size) {
            return true
        }
    }
    return false
}

