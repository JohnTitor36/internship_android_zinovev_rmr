package com.lockwood.pin.keyboard.adapter.holder

import com.lockwood.pin.databinding.ItemPinDigitBinding
import com.lockwood.pin.keyboard.adapter.PinAdapter.Companion.PIN_MAX_COUNT
import com.lockwood.pin.keyboard.adapter.base.BaseViewHolder
import com.lockwood.pin.keyboard.listener.PinKeyboardListener

@ExperimentalStdlibApi
internal class PinDigitViewHolder(
    itemBinding: ItemPinDigitBinding,
    listeners: List<PinKeyboardListener>,
    private val enteredDigits: List<Int>
) : BaseViewHolder<Int>(itemBinding, listeners) {

    private val digits: String
        get() = enteredDigits.joinToString(separator = "", transform = Int::toString)

    override fun onBind(item: Int) {

        with((itemBinding as ItemPinDigitBinding).itemPinDigitButton) {
            text = item.toString()
            setOnClickListener {
                enterDigit(item)
            }
        }
    }

    private fun enterDigit(digit: Int) {
        checkDigitClick(digit)
        checkLastItemEntered()
    }

    private fun checkDigitClick(digit: Int) {
        val notLastDigit = enteredDigits.size + 1 <= PIN_MAX_COUNT
        if (notLastDigit) {
            listeners.forEach { it.onDigitClick(digit) }
        }
    }

    private fun checkLastItemEntered() {
        val lastItemEntered = enteredDigits.size == PIN_MAX_COUNT
        if (lastItemEntered) {
            listeners.forEach { it.onLastItemEntered(digits) }
        }
    }

}