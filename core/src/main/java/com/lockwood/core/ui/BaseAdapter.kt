package com.lockwood.core.ui

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(protected val data: MutableList<T>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    companion object {
        /** Тип View без данных */
        const val VIEW_TYPE_EMPTY = 0

        /** Тип View с программой */
        const val VIEW_TYPE_NORMAL = 1

        // Для отображения STUB view показываем только один item
        private const val STUB_VIEW_ITEMS_COUNT = 1
    }

    override fun getItemCount(): Int {
        return if (data.isNotEmpty()) {
            data.size
        } else {
            STUB_VIEW_ITEMS_COUNT
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

}
