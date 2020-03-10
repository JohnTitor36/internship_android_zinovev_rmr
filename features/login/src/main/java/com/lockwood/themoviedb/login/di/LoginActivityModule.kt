package com.lockwood.themoviedb.login.di

import com.lockwood.themoviedb.login.presentation.LoginActivity
import dagger.Module
import dagger.Provides

@Module
class LoginActivityModule(
    private val activity: LoginActivity
) {

    @Provides
    fun provideContext(): LoginActivity = activity

}