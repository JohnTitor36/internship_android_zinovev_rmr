package com.lockwood.pin.adapter.holder

import com.lockwood.pin.adapter.base.BaseViewHolder
import com.lockwood.pin.adapter.listener.PinKeyboardListener
import com.lockwood.pin.databinding.ItemPinEmptyBinding

internal class PinEmptyViewHolder(
    itemBinding: ItemPinEmptyBinding,
    listener: PinKeyboardListener
) : BaseViewHolder<Int>(itemBinding, listener) {

    override fun onBind(item: Int) = Unit

}