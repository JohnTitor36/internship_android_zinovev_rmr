package com.lockwood.themoviedb.login.domain.repository

import com.lockwood.themoviedb.login.domain.model.CreateRequestTokenResponse
import com.lockwood.themoviedb.login.domain.model.CreateSessionBody
import com.lockwood.themoviedb.login.domain.model.CreateSessionResponse
import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody
import io.reactivex.Completable
import io.reactivex.Single

interface AuthenticationRepository {

    fun fetchCurrentRequestToken(): String

    fun fetchCurrentSessionId(): String

    fun saveCurrentRequestToken(requestToken: String)

    fun saveCurrentSessionId(sessionId: String)

    fun createRequestToken(): Single<CreateRequestTokenResponse>

    fun validateTokenWithLogin(loginBody: ValidateWithLoginBody): Completable

    fun createSession(sessionBody: CreateSessionBody): Single<CreateSessionResponse>

}