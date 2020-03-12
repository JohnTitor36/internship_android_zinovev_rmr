package com.lockwood.themoviedb.login.domain

import com.lockwood.themoviedb.login.data.LoginInfo
import com.spotify.mobius.Effects.effects
import com.spotify.mobius.Next
import com.spotify.mobius.Next.*
import com.spotify.mobius.Update

class LoginUpdate : Update<LoginInfo, LoginEvent, LoginEffect> {

    companion object {
        private const val DUMMY_LOGIN = "admin"
        private const val DUMMY_PASSWORD = "rmr2020"
    }

    override fun update(
        model: LoginInfo,
        event: LoginEvent
    ): Next<LoginInfo, LoginEffect> = when (event) {
        is LoginLoginTextChanged -> {
            val nextModel = model.copy(login = event.login)
            next(nextModel)
        }
        is LoginPasswordTextChanged -> {
            val nextModel = model.copy(password = event.password)
            next(nextModel)
        }
        is LoginRequested -> {
            val isCorrectCredentials = checkIsCorrectCredentials(model.login, model.password)
            if (isCorrectCredentials) {
                dispatch<LoginInfo, LoginEffect>(effects(NotifyLoginComplete))
            } else {
                dispatch<LoginInfo, LoginEffect>(effects(NotifyLoginInvalidInfo))
            }
        }
        is NetworkStateEvent -> {
            noChange()
        }
    }

    private fun checkIsCorrectCredentials(login: CharSequence, password: String): Boolean {
        val isCorrectLogin = checkLogin(login)
        val isCorrectPassword = checkPassword(password)
        return isCorrectLogin && isCorrectPassword
    }

    private fun checkLogin(login: CharSequence) = login == DUMMY_LOGIN

    private fun checkPassword(pass: CharSequence) = pass == DUMMY_PASSWORD

}