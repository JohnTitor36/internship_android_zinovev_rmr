package com.lockwood.themoviedb.login.presentation.view

import com.lockwood.themoviedb.login.data.LoginInfo
import com.lockwood.themoviedb.login.domain.CredentialsValidator
import javax.inject.Inject

interface LoginViewDataMapper {

    fun loginToLoginViewData(model: LoginInfo): LoginViewData

}

class DefaultLoginViewDataMapper @Inject constructor(
    private val validator: CredentialsValidator
) : LoginViewDataMapper {

    override fun loginToLoginViewData(model: LoginInfo): LoginViewData {
        val login = model.login
        val password = model.password
        val isValidateCredentials = validator.validateCredentials(login, password)
        return LoginViewData(
            login = login,
            password = password,
            isValidInput = isValidateCredentials
        )
    }

}