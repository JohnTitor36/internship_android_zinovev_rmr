package com.lockwood.themoviedb.login.presentation.ui

import com.lockwood.core.extensions.EMPTY

data class LoginViewState(
    val login: String,
    val password: String,
    val errorMessage: String,
    val loading: Boolean,
    val validCredentials: Boolean,
    val keyboardOpened: Boolean
) {

    companion object {

        val initialState
            get() = LoginViewState(
                login = String.EMPTY,
                password = String.EMPTY,
                errorMessage = String.EMPTY,
                loading = false,
                validCredentials = false,
                keyboardOpened = false
            )

    }

}