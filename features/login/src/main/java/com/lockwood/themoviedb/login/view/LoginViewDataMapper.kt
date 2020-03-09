package com.lockwood.themoviedb.login.view

import com.lockwood.themoviedb.login.data.LoginInfo

object LoginViewDataMapper {

    private const val MIN_PASSWORD_LENGTH = 4
    private const val MIN_LOGIN_LENGTH = 4

    fun loginToLoginViewData(model: LoginInfo): LoginViewData {
        val login = model.login
        val password = model.password
        val isValidateCredentials = validateCredentials(login, password)
        return LoginViewData(
            login = login,
            password = password,
            isValidInput = isValidateCredentials
        )
    }

    private fun validateCredentials(login: CharSequence, password: String): Boolean {
        val isValidLogin = validateLogin(login)
        val isValidPassword = validatePassword(password)
        return isValidLogin && isValidPassword
    }

    private fun validateLogin(login: CharSequence) = login.length > MIN_LOGIN_LENGTH

    private fun validatePassword(pass: CharSequence) = pass.length > MIN_PASSWORD_LENGTH

}