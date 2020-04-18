package com.lockwood.pin.keyboard.adapter.base

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.lockwood.pin.keyboard.listener.PinKeyboardListener

internal abstract class BaseViewHolder<T>(
    protected val itemViewBinding: ViewBinding,
    protected val listener: PinKeyboardListener
) : RecyclerView.ViewHolder(itemViewBinding.root) {

    abstract fun onBind(item: T)

}