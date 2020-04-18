package com.lockwood.pin.indicator.listener

interface IndicatorGroupListener {

    fun onAllCleared()

    fun onDigitCleared()

    fun onDigitEntered()

    fun onShowError()

}