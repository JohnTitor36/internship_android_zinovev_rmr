package com.lockwood.pin.indicator.adapter.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

internal abstract class BaseViewHolder(
    protected val itemBinding: ViewBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    abstract fun onBind(position: Int, selectedItemsCount: Int, isShowError: Boolean)

}