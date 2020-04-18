package com.lockwood.pin.keyboard.adapter.holder

import com.lockwood.pin.databinding.ItemPinExitBinding
import com.lockwood.pin.keyboard.adapter.base.BaseViewHolder
import com.lockwood.pin.keyboard.listener.PinKeyboardListener

internal class PinExitViewHolder(
    itemBinding: ItemPinExitBinding,
    listeners: List<PinKeyboardListener>
) : BaseViewHolder<Int>(itemBinding, listeners) {

    override fun onBind(item: Int) {

        with((itemBinding as ItemPinExitBinding).itemPinExit) {
            setOnClickListener {
                onExitClick()
            }
        }
    }

    private fun onExitClick() {
        listeners.forEach(PinKeyboardListener::onExitClick)
    }

}