package com.lockwood.core.preferences.authentication

import android.content.SharedPreferences
import com.lockwood.core.cryptographic.Cryptographer
import com.lockwood.core.preferences.preference.encryptDelegate

class DefaultAuthenticationPreferences(
    sharedPreferences: SharedPreferences,
    cryptographer: Cryptographer
) :
    AuthenticationPreferences {

    companion object {

        private const val REQUEST_TOKEN_PREF_NAME = "com.lockwood.themoviedb.login.requestToken"
        private const val SESSION_ID_PREF_NAME = "com.lockwood.themoviedb.login.sessionId"

        private const val DEFAULT_REQUEST_TOKEN = ""
        private const val DEFAULT_SESSION_ID = ""
    }

    private var requestToken by sharedPreferences.encryptDelegate(
        REQUEST_TOKEN_PREF_NAME,
        DEFAULT_REQUEST_TOKEN,
        cryptographer
    )

    private var sessionId by sharedPreferences.encryptDelegate(
        SESSION_ID_PREF_NAME,
        DEFAULT_SESSION_ID,
        cryptographer
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