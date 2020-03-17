package com.lockwood.core.preferences.authentication

import android.content.SharedPreferences
import com.lockwood.core.preferences.di.qualifier.EncryptedPreferences
import com.lockwood.core.preferences.extensions.DelegatesExt
import javax.inject.Inject

class DefaultAuthenticationCache @Inject constructor(
    @EncryptedPreferences sharedPreferences: SharedPreferences
) : AuthenticationCache {

    companion object {

        private const val REQUEST_TOKEN_PREF_NAME = "com.lockwood.themoviedb.login.requestToken"
        private const val SESSION_ID_PREF_NAME = "com.lockwood.themoviedb.login.sessionId"
    }

    private var requestToken by DelegatesExt.preference(
        sharedPreferences,
        REQUEST_TOKEN_PREF_NAME,
        ""
    )

    private var sessionId by DelegatesExt.preference(
        sharedPreferences,
        SESSION_ID_PREF_NAME,
        -1
    )

    override fun fetchCurrentRequestToken(): String {
        return requestToken
    }

    override fun fetchCurrentSessionId(): Int {
        return sessionId
    }

    override fun saveCurrentRequestToken(requestToken: String) {
        this.requestToken = requestToken
    }

    override fun saveCurrentSessionId(sessionId: Int) {
        this.sessionId = sessionId
    }

}