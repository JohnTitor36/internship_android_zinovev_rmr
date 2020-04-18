package com.lockwood.pin.keyboard.adapter.holder

import com.lockwood.pin.databinding.ItemPinEmptyBinding
import com.lockwood.pin.keyboard.adapter.base.BaseViewHolder
import com.lockwood.pin.keyboard.listener.PinKeyboardListener

internal class PinEmptyViewHolder(
    itemBinding: ItemPinEmptyBinding,
    listeners: List<PinKeyboardListener>
) : BaseViewHolder<Int>(itemBinding, listeners) {

    override fun onBind(item: Int) = Unit

}