package com.lockwood.themoviedb.login.domain

import com.lockwood.themoviedb.login.data.LoginModel
import com.spotify.mobius.Effects.effects
import com.spotify.mobius.Next
import com.spotify.mobius.Next.*
import com.spotify.mobius.Update

class LoginUpdate : Update<LoginModel, LoginEvent, LoginEffect> {

    companion object {
        private const val DUMMY_LOGIN = "admin"
        private const val DUMMY_PASSWORD = "rmr2020"
    }

    override fun update(
        model: LoginModel,
        event: LoginEvent
    ): Next<LoginModel, LoginEffect> = when (event) {
        is LoginLoginTextChanged -> {
            val nextModel = model.copy(login = model.login)
            next(nextModel)
        }
        is LoginPasswordTextChanged -> {
            val nextModel = model.copy(password = model.password)
            next(nextModel)
        }
        is LoginRequested -> {
            val isCorrectCredentials = checkCredentials(model.login, model.password)
            if (isCorrectCredentials) {
                dispatch<LoginModel, LoginEffect>(effects(NotifyLoginComplete))
            } else {
                dispatch<LoginModel, LoginEffect>(effects(NotifyLoginInvalidInfo))
            }
        }
        is NetworkStateEvent -> {
            noChange()
        }
    }

    private fun checkCredentials(login: CharSequence, password: String): Boolean {
        val isCorrectLogin = checkLogin(login)
        val isCorrectPassword = checkPassword(password)
        return isCorrectLogin && isCorrectPassword
    }

    private fun checkLogin(login: CharSequence) = login == DUMMY_LOGIN

    private fun checkPassword(pass: CharSequence) = pass == DUMMY_PASSWORD

}