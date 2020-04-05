package com.lockwood.themoviedb.login.utils

object CredentialsValidator {

    private const val REGEX_LOGIN = "^[a-zA-Z0-9_]{4,}"
    private const val REGEX_PASSWORD = "^[a-zA-Z0-9_@%+/\'!#^?:.(){}~]{4,}"

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

}