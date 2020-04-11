package com.lockwood.core.preferences.authentication

import android.content.SharedPreferences
import com.lockwood.core.extensions.EMPTY
import com.lockwood.core.preferences.preference.delegate

class DefaultAuthenticationPreferences(sharedPreferences: SharedPreferences) :
    AuthenticationPreferences {

    companion object {

        private const val REQUEST_TOKEN_PREF_NAME = "com.lockwood.themoviedb.login.requestToken"
        private const val SESSION_ID_PREF_NAME = "com.lockwood.themoviedb.login.sessionId"

        private val DEFAULT_REQUEST_TOKEN = String.EMPTY
        private val DEFAULT_SESSION_ID = String.EMPTY
    }

    private var requestToken by sharedPreferences.delegate(
        REQUEST_TOKEN_PREF_NAME,
        DEFAULT_REQUEST_TOKEN
    )

    private var sessionId by sharedPreferences.delegate(
        SESSION_ID_PREF_NAME,
        DEFAULT_SESSION_ID
    )

    override fun fetchCurrentRequestToken(): String {
        return requestToken
    }

    override fun fetchCurrentSessionId(): String {
        return sessionId
    }

    override fun saveCurrentRequestToken(value: String) {
        requestToken = value
    }

    override fun saveCurrentSessionId(value: String) {
        sessionId = value
    }

    override fun resetCurrentRequestToken() = saveCurrentRequestToken(DEFAULT_REQUEST_TOKEN)

    override fun resetCurrentSessionId() = saveCurrentSessionId(DEFAULT_SESSION_ID)

}