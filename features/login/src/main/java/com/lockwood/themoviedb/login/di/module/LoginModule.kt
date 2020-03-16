package com.lockwood.themoviedb.login.di.module

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.data.DefaultAuthenticationRepository
import com.lockwood.themoviedb.login.data.repository.AuthenticationRemote
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.lockwood.themoviedb.login.remote.DefaultAuthenticationRemote
import dagger.Binds
import dagger.Module

@Module
abstract class LoginModule {

    @Binds
    @FeatureScope
    abstract fun provideAuthenticationRepository(authentication: DefaultAuthenticationRepository): AuthenticationRepository

    @Binds
    @FeatureScope
    abstract fun provideAuthenticationRemote(authentication: DefaultAuthenticationRemote): AuthenticationRemote

}