package com.lockwood.themoviedb.login.di.module

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.lockwood.core.di.mapkey.ViewModelKey
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.toaster.Toaster
import com.lockwood.themoviedb.login.domain.CredentialsValidator
import com.lockwood.themoviedb.login.presentation.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class LoginModule {

    @Provides
    @FeatureScope
    fun provideToaster(context: Context) = Toaster(context)

    @Provides
    @FeatureScope
    fun provideCredentialsValidator() = CredentialsValidator()

    @Provides
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun provideFeatureViewModel(toaster: Toaster): ViewModel = LoginViewModel(toaster)

}