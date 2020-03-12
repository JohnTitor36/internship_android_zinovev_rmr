package com.lockwood.themoviedb.login.di.module

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.toaster.Toaster
import com.lockwood.themoviedb.login.domain.CredentialsValidator
import com.lockwood.themoviedb.login.presentation.LoginActivity
import com.lockwood.themoviedb.login.presentation.effecthandlers.LoginEffectHandlers
import com.lockwood.themoviedb.login.presentation.view.LoginViewDataMapper
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule(private val activity: LoginActivity) {

    @Provides
    @FeatureScope
    fun provideToaster() = Toaster(activity)

    @Provides
    @FeatureScope
    fun provideCredentialsValidator() = CredentialsValidator()

    @Provides
    @FeatureScope
    fun provideLoginEffectHandlers() = LoginEffectHandlers()

    @Provides
    @FeatureScope
    fun provideLoginViewDataMapper(validator: CredentialsValidator) = LoginViewDataMapper(validator)

}