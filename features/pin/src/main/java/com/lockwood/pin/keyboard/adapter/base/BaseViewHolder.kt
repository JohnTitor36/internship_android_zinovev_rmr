package com.lockwood.pin.keyboard.adapter.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.lockwood.pin.keyboard.listener.PinKeyboardListener

internal abstract class BaseViewHolder<T>(
    protected val itemBinding: ViewBinding,
    protected val listeners: List<PinKeyboardListener>
) : RecyclerView.ViewHolder(itemBinding.root) {

    abstract fun onBind(item: T)

}