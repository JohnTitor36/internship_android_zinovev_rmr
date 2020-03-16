package com.lockwood.core.cache.di.provider

import com.lockwood.core.cache.authentication.AuthenticationCache
import com.lockwood.core.cache.di.qualifier.RequestToken
import com.lockwood.core.cache.di.qualifier.SessionId

interface CacheApiProvider {

    @RequestToken
    fun provideRequestToken(): String

    @SessionId
    fun provideSessionId(): Int

    fun provideAuthenticationCache(): AuthenticationCache

}