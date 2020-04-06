package com.lockwood.themoviedb.login.di.module

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.remote.AuthenticationService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ServiceModule {

    @Provides
    @FeatureScope
    fun provideAuthenticationService(retrofit: Retrofit.Builder): AuthenticationService {
        return retrofit.build().create(AuthenticationService::class.java)
    }

}