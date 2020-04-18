package com.lockwood.pin.keyboard.adapter.holder

import com.lockwood.pin.databinding.ItemPinClearBinding
import com.lockwood.pin.keyboard.adapter.base.BaseViewHolder
import com.lockwood.pin.keyboard.listener.PinKeyboardListener

internal class PinClearViewHolder(
    itemBinding: ItemPinClearBinding,
    listeners: List<PinKeyboardListener>
) : BaseViewHolder<Int>(itemBinding, listeners) {

    override fun onBind(item: Int) {

        with((itemBinding as ItemPinClearBinding).itemPinIcon) {
            setOnClickListener {
                clearDigitClick()
            }
        }
    }

    private fun clearDigitClick() {
        listeners.forEach(PinKeyboardListener::onClearDigitClick)
    }

}