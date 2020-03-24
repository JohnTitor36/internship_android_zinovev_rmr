package com.lockwood.core.network.validator

import com.lockwood.core.preferences.authentication.AuthenticationPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiValuesValidator @Inject constructor(
    private val authenticationPreferences: AuthenticationPreferences
) {

    val isValidToken: Boolean
        get() {
            val token = authenticationPreferences.fetchCurrentRequestToken()
            return token.isNotEmpty()
        }

    val isValidSessionId: Boolean
        get() {
            val sessionId = authenticationPreferences.fetchCurrentSessionId()
            return sessionId.isNotEmpty()
        }

    fun resetApiValues(){
        authenticationPreferences.resetCurrentRequestToken()
        authenticationPreferences.resetCurrentSessionId()
    }

}