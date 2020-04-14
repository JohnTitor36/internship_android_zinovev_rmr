package com.lockwood.themoviedb.login.presentation.ui.pin

import com.lockwood.core.extensions.EMPTY

data class PinViewState(
    val pin: String,
    val errorMessage: String,
    val repeatNewPin: Boolean
) {

    companion object {

        val initialState
            get() = PinViewState(
                pin = String.EMPTY,
                errorMessage = String.EMPTY,
                repeatNewPin = false
            )
    }

}