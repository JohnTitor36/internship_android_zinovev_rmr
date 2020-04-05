package com.lockwood.themoviedb.login.presentation.ui

data class LoginViewState(
    val login: String,
    val password: String,
    val errorMessage: String,
    val loading: Boolean,
    val validCredentials: Boolean,
    val keyboardOpened: Boolean
)