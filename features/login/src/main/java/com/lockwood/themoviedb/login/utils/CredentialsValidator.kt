package com.lockwood.themoviedb.login.utils

object CredentialsValidator {

    private const val MIN_PASSWORD_LENGTH = 4
    private const val MIN_LOGIN_LENGTH = 4

    private const val REGEX_LOGIN = "^[a-zA-Z0-9_]{4,}"
    private const val REGEX_PASSWORD = "^[a-zA-Z0-9_@%+/\'!#^?:.(){}~]{4,}"

    fun isValidLength(login: String, password: String): Boolean {
        val isValidLogin = isValidLoginLength(login)
        val isValidPassword = isValidPasswordLength(password)
        return isValidLogin && isValidPassword
    }

    fun isValidInput(login: String, password: String): Boolean {
        val isValidLogin = isValidLogin(login)
        val isValidPassword = isValidPassword(password)
        return isValidLogin && isValidPassword
    }

    private fun isValidLogin(login: CharSequence): Boolean {
        return login.matches(REGEX_LOGIN.toRegex())
    }

    private fun isValidPassword(pass: CharSequence): Boolean {
        return pass.matches(REGEX_PASSWORD.toRegex())
    }

    private fun isValidLoginLength(login: CharSequence): Boolean {
        return login.length > MIN_LOGIN_LENGTH
    }

    private fun isValidPasswordLength(pass: CharSequence): Boolean {
        return pass.length > MIN_PASSWORD_LENGTH
    }

}