package com.lockwood.pin.keyboard.adapter.holder

import com.lockwood.pin.keyboard.adapter.base.BaseViewHolder
import com.lockwood.pin.keyboard.listener.PinKeyboardListener
import com.lockwood.pin.databinding.ItemPinClearBinding

@ExperimentalStdlibApi
internal class PinClearViewHolder(
    itemBinding: ItemPinClearBinding,
    listener: PinKeyboardListener,
    private val enteredDigits: MutableList<Int>
) : BaseViewHolder<Int>(itemBinding, listener) {

    override fun onBind(item: Int) {

        with(itemViewBinding as ItemPinClearBinding){
            itemPinIcon.setOnClickListener { performClick() }
        }
    }

    private fun performClick(){
        enteredDigits.removeLastOrNull()
        listener.onClearDigitClick()
    }

}