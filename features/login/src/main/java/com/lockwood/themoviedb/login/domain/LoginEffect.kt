package com.lockwood.themoviedb.login.domain

sealed class LoginEffect

object NotifyLoginComplete : LoginEffect()
object NotifyLoginInvalidInfo : LoginEffect()