package com.lockwood.themoviedb.login.data.repository

import com.lockwood.themoviedb.login.data.model.CreateRequestTokenResponseEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionBodyEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionResponseEntity
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import io.reactivex.Completable
import io.reactivex.Single

interface AuthenticationRemote {

    fun createRequestToken(apiKey: String): Single<CreateRequestTokenResponseEntity>

    fun validateTokenWithLogin(
        apiKey: String, loginBody:
        ValidateWithLoginBodyEntity
    ): Completable

    fun createSession(
        apiKey: String,
        sessionBody: CreateSessionBodyEntity
    ): Single<CreateSessionResponseEntity>

}