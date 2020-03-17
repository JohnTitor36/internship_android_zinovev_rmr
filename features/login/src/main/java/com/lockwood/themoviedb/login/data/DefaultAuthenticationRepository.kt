package com.lockwood.themoviedb.login.data

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.data.mapper.CreateRequestTokenResponseMapper
import com.lockwood.themoviedb.login.data.mapper.CreateSessionBodyMapper
import com.lockwood.themoviedb.login.data.mapper.CreateSessionResponseMapper
import com.lockwood.themoviedb.login.data.mapper.ValidateWithLoginBodyMapper
import com.lockwood.themoviedb.login.data.source.AuthenticationCacheDataSource
import com.lockwood.themoviedb.login.data.source.AuthenticationRemoteDataSource
import com.lockwood.themoviedb.login.domain.model.CreateRequestTokenResponse
import com.lockwood.themoviedb.login.domain.model.CreateSessionBody
import com.lockwood.themoviedb.login.domain.model.CreateSessionResponse
import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class DefaultAuthenticationRepository @Inject constructor(
    private val authenticationCacheDataSource: AuthenticationCacheDataSource,
    private val authenticationRemoteDataSource: AuthenticationRemoteDataSource,
    private val createRequestTokenResponseMapper: CreateRequestTokenResponseMapper,
    private val createSessionBodyMapper: CreateSessionBodyMapper,
    private val createSessionResponseMapper: CreateSessionResponseMapper,
    private val validateWithLoginBodyMapper: ValidateWithLoginBodyMapper
) : AuthenticationRepository {

    override fun fetchCurrentRequestToken(): String {
        return authenticationCacheDataSource.fetchCurrentRequestToken()
    }

    override fun fetchCurrentSessionId(): String {
        return authenticationCacheDataSource.fetchCurrentSessionId()
    }

    override fun saveCurrentRequestToken(requestToken: String) {
        return authenticationCacheDataSource.saveCurrentRequestToken(requestToken)
    }

    override fun saveCurrentSessionId(sessionId: String) {
        return authenticationCacheDataSource.saveCurrentSessionId(sessionId)
    }

    override fun createRequestToken(apiKey: String): Single<CreateRequestTokenResponse> {
        return authenticationRemoteDataSource.createRequestToken(apiKey)
            .map { createRequestTokenResponseMapper.mapFromEntity(it) }
    }

    override fun validateTokenWithLogin(
        apiKey: String,
        loginBody: ValidateWithLoginBody
    ): Completable {
        val loginBodyEntity = validateWithLoginBodyMapper.mapToRemote(loginBody)
        return authenticationRemoteDataSource.validateTokenWithLogin(apiKey, loginBodyEntity)
    }

    override fun createSession(
        apiKey: String,
        sessionBody: CreateSessionBody
    ): Single<CreateSessionResponse> {
        val sessionBodyEntity = createSessionBodyMapper.mapToRemote(sessionBody)
        return authenticationRemoteDataSource.createSession(apiKey, sessionBodyEntity)
            .map { createSessionResponseMapper.mapFromRemote(it) }
    }

}