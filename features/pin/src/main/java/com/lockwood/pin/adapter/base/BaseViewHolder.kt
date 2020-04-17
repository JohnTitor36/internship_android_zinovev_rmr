package com.lockwood.pin.adapter.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.lockwood.pin.adapter.listener.PinKeyboardListener

internal abstract class BaseViewHolder<T>(
    protected val itemViewBinding: ViewBinding,
    protected val listener: PinKeyboardListener
) : RecyclerView.ViewHolder(itemViewBinding.root) {

    abstract fun onBind(item: T)

}