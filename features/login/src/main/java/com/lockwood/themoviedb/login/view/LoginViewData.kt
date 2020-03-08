package com.lockwood.themoviedb.login.view

data class LoginViewData(
    val login: String = "",
    val password: String = "",
    val isValidInput: Boolean = false
)