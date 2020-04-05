package com.lockwood.themoviedb.login.data.repository

import com.lockwood.themoviedb.login.data.model.CreateRequestTokenResponseEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionBodyEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionResponseEntity
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import io.reactivex.Completable
import io.reactivex.Single

interface AuthenticationDataStore {

    fun fetchCurrentRequestToken(): String

    fun fetchCurrentSessionId(): String

    fun saveCurrentRequestToken(requestToken: String)

    fun saveCurrentSessionId(sessionId: String)

    fun createRequestToken(): Single<CreateRequestTokenResponseEntity>

    fun validateTokenWithLogin(
        loginBody: ValidateWithLoginBodyEntity
    ): Completable

    fun createSession(
        sessionBody: CreateSessionBodyEntity
    ): Single<CreateSessionResponseEntity>

}