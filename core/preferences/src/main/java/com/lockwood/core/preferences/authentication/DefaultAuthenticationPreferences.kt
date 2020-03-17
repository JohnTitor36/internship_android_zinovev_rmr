package com.lockwood.core.preferences.authentication

import android.content.SharedPreferences
import com.lockwood.core.preferences.extensions.DelegatesExt

class DefaultAuthenticationPreferences(sharedPreferences: SharedPreferences) : AuthenticationPreferences {

    companion object {

        private const val REQUEST_TOKEN_PREF_NAME = "com.lockwood.themoviedb.login.requestToken"
        private const val SESSION_ID_PREF_NAME = "com.lockwood.themoviedb.login.sessionId"

        private const val DEF_REQUEST_TOKEN = ""
        private const val DEF_SESSION_ID = -1
    }

    private var requestToken by DelegatesExt.preference(
        sharedPreferences,
        REQUEST_TOKEN_PREF_NAME,
        DEF_REQUEST_TOKEN
    )

    private var sessionId by DelegatesExt.preference(
        sharedPreferences,
        SESSION_ID_PREF_NAME,
        DEF_SESSION_ID
    )

    override fun fetchCurrentRequestToken(): String {
        return requestToken
    }

    override fun fetchCurrentSessionId(): Int {
        return sessionId
    }

    override fun saveCurrentRequestToken(value: String) {
        requestToken = value
    }

    override fun saveCurrentSessionId(value: Int) {
        sessionId = value
    }

}