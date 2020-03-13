package com.lockwood.themoviedb.login.di.module

import android.content.Context
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.toaster.Toaster
import com.lockwood.themoviedb.login.domain.CredentialsValidator
import dagger.Module
import dagger.Provides

@Module
class LoginModule {

    @Provides
    @FeatureScope
    fun provideToaster(context: Context) = Toaster(context)

    @Provides
    @FeatureScope
    fun provideCredentialsValidator() = CredentialsValidator()

}