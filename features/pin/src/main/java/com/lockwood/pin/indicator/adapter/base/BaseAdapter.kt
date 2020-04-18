package com.lockwood.pin.indicator.adapter.base

import androidx.recyclerview.widget.RecyclerView

internal abstract class BaseAdapter(
    protected open var selectedItemsCount: Int,
    protected open var isShowError: Boolean
) : RecyclerView.Adapter<BaseViewHolder>() {

    companion object {

        private const val INDICATOR_ITEM_COUNT = 4
    }

    override fun getItemCount(): Int {
        return INDICATOR_ITEM_COUNT
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position, selectedItemsCount, isShowError)
    }

}