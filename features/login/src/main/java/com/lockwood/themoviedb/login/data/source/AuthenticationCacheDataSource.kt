package com.lockwood.themoviedb.login.data.source

import com.lockwood.core.cache.authentication.AuthenticationCache
import com.lockwood.themoviedb.login.data.model.CreateRequestTokenResponseEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionBodyEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionResponseEntity
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import com.lockwood.themoviedb.login.data.repository.AuthenticationDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthenticationCacheDataSource @Inject constructor(
    private val authenticationCache: AuthenticationCache
) : AuthenticationDataStore {

    override fun fetchCurrentRequestToken() =
        authenticationCache.fetchCurrentRequestToken()

    override fun fetchCurrentSessionId() =
        authenticationCache.fetchCurrentSessionId()

    override fun saveCurrentRequestToken(requestToken: String) =
        authenticationCache.saveCurrentRequestToken(requestToken)

    override fun saveCurrentSessionId(sessionId: Int) =
        authenticationCache.saveCurrentSessionId(sessionId)

    override fun createRequestToken(apiKey: String): Single<CreateRequestTokenResponseEntity> =
        throw UnsupportedOperationException()

    override fun validateTokenWithLogin(
        apiKey: String,
        loginBody: ValidateWithLoginBodyEntity
    ): Completable = throw UnsupportedOperationException()

    override fun createSession(
        apiKey: String,
        sessionBody: CreateSessionBodyEntity
    ): Single<CreateSessionResponseEntity> = throw UnsupportedOperationException()

}