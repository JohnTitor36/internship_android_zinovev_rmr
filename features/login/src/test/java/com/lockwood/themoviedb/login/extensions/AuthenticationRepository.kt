package com.lockwood.themoviedb.login.extensions

import com.lockwood.themoviedb.login.data.DefaultAuthenticationRepository
import com.lockwood.themoviedb.login.data.mapper.CreateRequestTokenResponseMapper
import com.lockwood.themoviedb.login.data.mapper.CreateSessionBodyMapper
import com.lockwood.themoviedb.login.data.mapper.CreateSessionResponseMapper
import com.lockwood.themoviedb.login.data.mapper.ValidateWithLoginBodyMapper
import com.lockwood.themoviedb.login.data.repository.AuthenticationRemote
import com.lockwood.themoviedb.login.data.source.AuthenticationCacheDataSource
import com.lockwood.themoviedb.login.data.source.AuthenticationRemoteDataSource
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.lockwood.themoviedb.login.remote.AuthenticationService
import com.lockwood.themoviedb.login.remote.DefaultAuthenticationRemote
import com.lockwood.themoviedb.login.remote.mapper.CreateRequestTokenResponseEntityMapper
import com.lockwood.themoviedb.login.remote.mapper.CreateSessionBodyEntityMapper
import com.lockwood.themoviedb.login.remote.mapper.CreateSessionResponseEntityMapper
import com.lockwood.themoviedb.login.remote.mapper.ValidateWithLoginBodyEntityMapper
import com.nhaarman.mockitokotlin2.mock

fun createAuthenticationRepository(
    remoteDataSource: AuthenticationRemoteDataSource,
    cacheDataSource: AuthenticationCacheDataSource = AuthenticationCacheDataSource(mock())
): AuthenticationRepository {
    return DefaultAuthenticationRepository(
        authenticationRemoteDataSource = remoteDataSource,
        authenticationCacheDataSource = cacheDataSource,
        createRequestTokenResponseMapper = CreateRequestTokenResponseMapper(),
        createSessionBodyMapper = CreateSessionBodyMapper(),
        createSessionResponseMapper = CreateSessionResponseMapper(),
        validateWithLoginBodyMapper = ValidateWithLoginBodyMapper()
    )
}

fun createAuthenticationRemoteDataSource(
    authenticationRemote: AuthenticationRemote
): AuthenticationRemoteDataSource {
    return AuthenticationRemoteDataSource(
        authenticationRemote = authenticationRemote
    )
}

fun createAuthenticationRemote(
    authenticationService: AuthenticationService
): AuthenticationRemote {
    return DefaultAuthenticationRemote(
        authenticationService = authenticationService,
        createRequestTokenResponseEntityMapper = CreateRequestTokenResponseEntityMapper(),
        createSessionResponseEntityMapper = CreateSessionResponseEntityMapper(),
        createSessionBodyEntityMapper = CreateSessionBodyEntityMapper(),
        validateWithLoginBodyEntityMapper = ValidateWithLoginBodyEntityMapper()
    )
}