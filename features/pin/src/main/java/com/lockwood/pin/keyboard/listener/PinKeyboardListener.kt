package com.lockwood.pin.keyboard.listener

interface PinKeyboardListener {

    fun onDigitClick(digit: Int)

    fun onClearDigitClick()

    fun onLastItemEntered(digits: String)

    fun onExitClick()

}