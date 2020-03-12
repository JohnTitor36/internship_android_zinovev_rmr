package com.lockwood.themoviedb.login.di.module

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.core.toaster.Toaster
import com.lockwood.themoviedb.login.presentation.LoginActivity
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule(private val activity: LoginActivity) {

    @Provides
    fun provideContext(): LoginActivity = activity

    @Provides
    @FeatureScope
    fun provideToaster(): Toaster = Toaster(activity)

}