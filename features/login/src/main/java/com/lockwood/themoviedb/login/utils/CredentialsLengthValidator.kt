package com.lockwood.themoviedb.login.utils

object CredentialsLengthValidator {

    private const val MIN_PASSWORD_LENGTH = 4
    private const val MIN_LOGIN_LENGTH = 4

    fun validateLength(login: String, password: String): Boolean {
        val isValidLogin = validateLogin(login)
        val isValidPassword = validatePassword(password)
        return isValidLogin && isValidPassword
    }

    private fun validateLogin(login: CharSequence) = login.length > MIN_LOGIN_LENGTH

    private fun validatePassword(pass: CharSequence) = pass.length > MIN_PASSWORD_LENGTH

}