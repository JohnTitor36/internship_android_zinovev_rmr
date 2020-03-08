package com.lockwood.themoviedb.login.domain

sealed class LoginEvent

data class LoginLoginTextChanged(val login: String) : LoginEvent()
data class LoginPasswordTextChanged(val password: String) : LoginEvent()
object LoginRequested : LoginEvent()
data class NetworkStateEvent(val connected: Boolean) : LoginEvent()