package com.lockwood.pin.adapter.holder

import com.lockwood.pin.adapter.base.BaseViewHolder
import com.lockwood.pin.adapter.listener.PinKeyboardListener
import com.lockwood.pin.databinding.ItemPinExitBinding

internal class PinExitViewHolder(
    itemBinding: ItemPinExitBinding,
    listener: PinKeyboardListener
) : BaseViewHolder<Int>(itemBinding, listener) {

    override fun onBind(item: Int) {

        with(itemViewBinding as ItemPinExitBinding) {
            itemPinExit.setOnClickListener { listener.onExitClick() }
        }
    }

}