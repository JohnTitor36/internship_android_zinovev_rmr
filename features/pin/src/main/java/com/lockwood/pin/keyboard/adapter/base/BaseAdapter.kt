package com.lockwood.pin.keyboard.adapter.base

import androidx.recyclerview.widget.RecyclerView

internal abstract class BaseAdapter<T>(
    protected val data: List<T>,
    protected val enteredDigits: MutableList<Int>
) : RecyclerView.Adapter<BaseViewHolder<T>>() {

    companion object {
        const val VIEW_TYPE_EMPTY = -1

        private const val STUB_VIEW_ITEMS_COUNT = 1
    }

    override fun getItemCount(): Int {
        return if (data.isNotEmpty()) {
            data.size
        } else {
            STUB_VIEW_ITEMS_COUNT
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.onBind(data[position])
    }

}