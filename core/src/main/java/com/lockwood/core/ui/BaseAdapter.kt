package com.lockwood.core.ui

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(protected val data: MutableList<T>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun getItemCount(): Int {
        return if (data.isNotEmpty()) {
            data.size
        } else {
            1
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (!data.isNullOrEmpty()) {
            VIEW_TYPE_NORMAL
        } else {
            VIEW_TYPE_EMPTY
        }
    }

    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int
    ) {
        holder.onBind(position)
    }

    fun addItems(list: List<T>) {
        data.addAll(list)
        notifyDataSetChanged()
    }

    /**
     * Очистить данные
     */
    fun clearItems() {
        data.clear()
    }

    companion object {
        /** Тип View без данных */
        const val VIEW_TYPE_EMPTY = 0

        /** Тип View с программой */
        const val VIEW_TYPE_NORMAL = 1
    }
}
