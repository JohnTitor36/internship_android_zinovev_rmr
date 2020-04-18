package com.lockwood.pin.keyboard.adapter.holder

import com.lockwood.pin.keyboard.adapter.base.BaseViewHolder
import com.lockwood.pin.keyboard.listener.PinKeyboardListener
import com.lockwood.pin.databinding.ItemPinDigitBinding

internal class PinDigitViewHolder(
    itemBinding: ItemPinDigitBinding,
    listener: PinKeyboardListener,
    private val enteredDigits: MutableList<Int>,
    private val resetEnteredDigits: () -> Unit
) : BaseViewHolder<Int>(itemBinding, listener) {

    companion object {

        // Пока через константу, но можно будет вынести в attrs
        private const val PIN_MAX_COUNT = 4
    }

    private val digits: String
        get() = enteredDigits.joinToString(separator = "", transform = Int::toString)

    override fun onBind(item: Int) {

        with((itemViewBinding as ItemPinDigitBinding).itemPinDigitButton) {
            text = item.toString()
            setOnClickListener { performClick(item) }
        }
    }

    private fun performClick(digit: Int) {
        enteredDigits.add(digit)
        listener.onDigitClick(digit)
        checkLastItemEntered()
    }

    private fun checkLastItemEntered() {
        if (enteredDigits.size == PIN_MAX_COUNT) {
            listener.onLastItemEntered(digits)
            resetEnteredDigits.invoke()
        }
    }

}