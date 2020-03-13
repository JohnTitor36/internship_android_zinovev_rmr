package com.lockwood.themoviedb.login.di.module

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.toaster.Toaster
import com.lockwood.themoviedb.login.domain.CredentialsValidator
import com.lockwood.themoviedb.login.presentation.LoginActivity
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

}