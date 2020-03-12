package com.lockwood.themoviedb.login.presentation.view

import com.lockwood.themoviedb.login.data.LoginInfo
import com.lockwood.themoviedb.login.domain.CredentialsValidator
import javax.inject.Inject

class LoginViewDataMapper @Inject constructor(
    private val validator: CredentialsValidator
) {

    fun loginToLoginViewData(model: LoginInfo): LoginViewData {
        val login = model.login
        val password = model.password
        val isValidateCredentials = validator.validateCredentialsLength(login, password)
        return LoginViewData(
            login = login,
            password = password,
            isValidInput = isValidateCredentials
        )
    }

}