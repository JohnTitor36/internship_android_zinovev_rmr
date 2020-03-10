package com.lockwood.themoviedb.login.di

import com.lockwood.themoviedb.login.presentation.LoginActivity

fun LoginActivity.inject() {
    DaggerLoginComponent.builder()
        .activity(this)
        .loginActivityModule(LoginActivityModule(this))
        .build()
        .inject(this)
}