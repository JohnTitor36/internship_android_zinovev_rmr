package com.lockwood.core.router

import android.content.Context
import android.content.Intent
import com.lockwood.core.extensions.launchActivity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginActivityRouter @Inject constructor(
    private val context: Context
) {

    companion object {

        private const val LOGIN_ACTIVITY_CLASS_NAME =
            "com.lockwood.themoviedb.login.presentation.ui.LoginActivity"
    }

    fun openLoginActivity() = context.launchActivity(LOGIN_ACTIVITY_CLASS_NAME) {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

}