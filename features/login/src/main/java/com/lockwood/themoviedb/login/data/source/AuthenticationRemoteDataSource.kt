package com.lockwood.themoviedb.login.data.source

import com.lockwood.themoviedb.login.data.model.CreateRequestTokenResponseEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionBodyEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionResponseEntity
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import com.lockwood.themoviedb.login.data.repository.AuthenticationDataStore
import com.lockwood.themoviedb.login.data.repository.AuthenticationRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthenticationRemoteDataSource @Inject constructor(
    private val authenticationRemote: AuthenticationRemote
) : AuthenticationDataStore {

    override fun fetchCurrentRequestToken() =
        throw UnsupportedOperationException()

    override fun fetchCurrentSessionId() =
        throw UnsupportedOperationException()

    override fun saveCurrentRequestToken(requestToken: String) =
        throw UnsupportedOperationException()

    override fun saveCurrentSessionId(sessionId: Int) =
        throw UnsupportedOperationException()

    override fun createRequestToken(apiKey: String): Single<CreateRequestTokenResponseEntity> =
        authenticationRemote.createRequestToken(apiKey)

    override fun validateTokenWithLogin(
        apiKey: String,
        loginBody: ValidateWithLoginBodyEntity
    ): Completable = authenticationRemote.validateTokenWithLogin(apiKey, loginBody)

    override fun createSession(
        apiKey: String,
        sessionBody: CreateSessionBodyEntity
    ): Single<CreateSessionResponseEntity> = authenticationRemote.createSession(apiKey, sessionBody)

}