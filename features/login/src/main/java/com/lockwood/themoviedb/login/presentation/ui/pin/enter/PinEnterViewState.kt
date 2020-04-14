package com.lockwood.themoviedb.login.presentation.ui.pin.enter

import com.lockwood.core.extensions.EMPTY

data class PinEnterViewState(
    val pin: String,
    val errorMessage: String,
    val repeatNewPin: Boolean
) {

    companion object {

        val initialState
            get() = PinEnterViewState(
                pin = String.EMPTY,
                errorMessage = String.EMPTY,
                repeatNewPin = false
            )
    }

}