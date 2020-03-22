package com.lockwood.themoviedb.login.utils

object CredentialsValidator {

    private const val MIN_PASSWORD_LENGTH = 4
    private const val MIN_LOGIN_LENGTH = 4

    fun isValidLength(login: String, password: String): Boolean {
        val isValidLogin = isValidLoginLength(login)
        val isValidPassword = isValidPasswordLength(password)
        return isValidLogin && isValidPassword
    }

    private fun isValidLoginLength(login: CharSequence) = login.length > MIN_LOGIN_LENGTH

    private fun isValidPasswordLength(pass: CharSequence) = pass.length > MIN_PASSWORD_LENGTH

}