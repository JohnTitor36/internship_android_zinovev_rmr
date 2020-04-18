package com.lockwood.pin.keyboard.listener

interface PinKeyboardListener {

    fun onDigitClick(digit: Int)

    fun onClearDigitClick()

    fun onExitClick()

    fun onResetEnteredDigits()

    fun onLastItemEntered(digits: String)

}