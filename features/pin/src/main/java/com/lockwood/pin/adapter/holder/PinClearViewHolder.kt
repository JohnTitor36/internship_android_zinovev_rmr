package com.lockwood.pin.adapter.holder

import com.lockwood.pin.adapter.base.BaseViewHolder
import com.lockwood.pin.adapter.listener.PinKeyboardListener
import com.lockwood.pin.databinding.ItemPinClearBinding

@ExperimentalStdlibApi
internal class PinClearViewHolder(
    itemBinding: ItemPinClearBinding,
    listener: PinKeyboardListener,
    private val enteredDigits: MutableList<Int>
) : BaseViewHolder<Int>(itemBinding, listener) {

    override fun onBind(item: Int) {
        enteredDigits.removeLast()

        with(itemViewBinding as ItemPinClearBinding){
            itemPinIcon.setOnClickListener { listener.onClearDigitClick() }
        }
    }

}