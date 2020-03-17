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
import com.squareup.moshi.Moshi
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject

@FeatureScope
class DefaultAuthenticationRemote @Inject constructor(
    private val moshi: Moshi,
    private val retrofit: Retrofit,
    private val createRequestTokenResponseEntityMapper: CreateRequestTokenResponseEntityMapper,
    private val createSessionResponseEntityMapper: CreateSessionResponseEntityMapper,
    private val createSessionBodyEntityMapper: CreateSessionBodyEntityMapper,
    private val validateWithLoginBodyEntityMapper: ValidateWithLoginBodyEntityMapper
) : AuthenticationRemote {

    private val authenticationService: AuthenticationService
        get() = retrofit.create(AuthenticationService::class.java)

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
