package com.lockwood.themoviedb.login.data.source

import com.lockwood.core.preferences.authentication.AuthenticationPreferences
import com.lockwood.themoviedb.login.data.model.CreateRequestTokenResponseEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionBodyEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionResponseEntity
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import com.lockwood.themoviedb.login.data.repository.AuthenticationDataStore
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AuthenticationCacheDataSource @Inject constructor(
    private val authenticationPreferences: AuthenticationPreferences
) : AuthenticationDataStore {

    override fun fetchCurrentRequestToken(): String {
        return authenticationPreferences.fetchCurrentRequestToken()
    }

    override fun fetchCurrentSessionId(): String {
        return authenticationPreferences.fetchCurrentSessionId()
    }

    override fun saveCurrentRequestToken(requestToken: String) {
        authenticationPreferences.saveCurrentRequestToken(requestToken)
    }

    override fun saveCurrentSessionId(sessionId: String) {
        authenticationPreferences.saveCurrentSessionId(sessionId)
    }

    override fun createRequestToken(): Single<CreateRequestTokenResponseEntity> {
        throw UnsupportedOperationException()
    }

    override fun validateTokenWithLogin(
        loginBody: ValidateWithLoginBodyEntity
    ): Completable {
        throw UnsupportedOperationException()
    }

    override fun createSession(
        sessionBody: CreateSessionBodyEntity
    ): Single<CreateSessionResponseEntity> {
        throw UnsupportedOperationException()
    }

}