package com.lockwood.themoviedb.login.di.module

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.remote.service.AuthenticationService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit

@Module
class ServiceModule {

    @Provides
    @FeatureScope
    fun provideAuthenticationService(
        httpClientBuilder: OkHttpClient.Builder,
        retrofit: Retrofit.Builder
    ): AuthenticationService {
        val noCacheClient = httpClientBuilder.cache(null).build()
        return retrofit.client(noCacheClient).build().create(AuthenticationService::class.java)
    }

}