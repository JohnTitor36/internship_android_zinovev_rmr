package com.lockwood.themoviedb.login.remote

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.data.model.CreateRequestTokenResponseEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionBodyEntity
import com.lockwood.themoviedb.login.data.model.CreateSessionResponseEntity
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import com.lockwood.themoviedb.login.data.repository.AuthenticationRemote
import com.lockwood.themoviedb.login.remote.mapper.CreateRequestTokenResponseEntityMapper
import com.lockwood.themoviedb.login.remote.mapper.CreateSessionBodyEntityMapper
import com.lockwood.themoviedb.login.remote.mapper.CreateSessionResponseEntityMapper
import com.lockwood.themoviedb.login.remote.mapper.ValidateWithLoginBodyEntityMapper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class DefaultAuthenticationRemote @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val createRequestTokenResponseEntityMapper: CreateRequestTokenResponseEntityMapper,
    private val createSessionResponseEntityMapper: CreateSessionResponseEntityMapper,
    private val createSessionBodyEntityMapper: CreateSessionBodyEntityMapper,
    private val validateWithLoginBodyEntityMapper: ValidateWithLoginBodyEntityMapper
) : AuthenticationRemote {

    override fun createRequestToken(apiKey: String): Single<CreateRequestTokenResponseEntity> {
        return authenticationService.createRequestToken(apiKey)
            .map { createRequestTokenResponseEntityMapper.mapFromRemote(it) }
    }

    override fun validateTokenWithLogin(
        apiKey: String,
        loginBody: ValidateWithLoginBodyEntity
    ): Completable {
        val loginBodyModel = validateWithLoginBodyEntityMapper.mapToRemote(loginBody)
        return authenticationService.validateTokenWithLogin(apiKey, loginBodyModel)
    }

    override fun createSession(
        apiKey: String,
        sessionBody: CreateSessionBodyEntity
    ): Single<CreateSessionResponseEntity> {
        val sessionBodyModel = createSessionBodyEntityMapper.mapToRemote(sessionBody)
        return authenticationService.createSession(apiKey, sessionBodyModel)
            .map { createSessionResponseEntityMapper.mapFromRemote(it) }
    }

}