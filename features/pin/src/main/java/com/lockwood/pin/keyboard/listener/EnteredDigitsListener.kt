package com.lockwood.pin.keyboard.listener

@ExperimentalStdlibApi
class EnteredDigitsListener(
    private val enteredDigits: MutableList<Int>
) : PinKeyboardListener {

    override fun onDigitClick(digit: Int) {
        enteredDigits.add(digit)
    }

    override fun onClearDigitClick() {
        enteredDigits.removeLastOrNull()
    }

    override fun onResetEnteredDigits() {
        enteredDigits.clear()
    }

    override fun onExitClick() = Unit

    override fun onLastItemEntered(digits: String) = Unit

}