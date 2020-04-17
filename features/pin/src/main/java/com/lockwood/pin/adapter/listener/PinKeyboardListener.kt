package com.lockwood.pin.adapter.listener

interface PinKeyboardListener {

    fun onDigitClick(digit: Int)

    fun onClearDigitClick()

    fun onLastItemEntered(digits: String)

    fun onExitClick()

}