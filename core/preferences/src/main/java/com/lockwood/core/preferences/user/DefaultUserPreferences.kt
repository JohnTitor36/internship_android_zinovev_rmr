package com.lockwood.core.preferences.user

import android.content.SharedPreferences
import com.lockwood.core.preferences.extensions.DelegatesExt

class DefaultUserPreferences(sharedPreferences: SharedPreferences) : UserPreferences {

    companion object {

        private const val IS_LOGGED_IN_PREF_NAME = "com.lockwood.themoviedb.login.isLoggedIn"

        private const val DEF_IS_LOGGED_IN = false
    }

    private var isLoggedIn by DelegatesExt.preference(
        sharedPreferences,
        IS_LOGGED_IN_PREF_NAME,
        DEF_IS_LOGGED_IN
    )

    override fun fetchUserLoggedIn(): Boolean {
        return isLoggedIn
    }

    override fun setUserLoggedIn(value: Boolean) {
        isLoggedIn = value
    }

}