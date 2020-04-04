package com.lockwood.themoviedb.login.generator

import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody

// Создал для примера
object ValidateWithLoginBodyGenerator {

    private const val VALID_LOGIN = "ajj_the_band"
    private const val VALID_PASSWORD = "123456"

    fun createBody(
        username: String = "",
        password: String = "",
        requestToken: String = ""
    ): ValidateWithLoginBody {
        return ValidateWithLoginBody(
            username = username,
            password = password,
            requestToken = requestToken
        )
    }

    fun createBodyWithValidCredentials(
        username: String = VALID_LOGIN,
        password: String = VALID_PASSWORD
    ): ValidateWithLoginBody {
        return createBody(
            username = username,
            password = password
        )
    }

}