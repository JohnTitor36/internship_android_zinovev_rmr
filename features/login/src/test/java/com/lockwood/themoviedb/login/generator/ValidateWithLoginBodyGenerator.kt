package com.lockwood.themoviedb.login.generator

import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody

object ValidateWithLoginBodyGenerator {

    fun createValidateWithLoginBody(
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

}