package com.lockwood.core.preferences.authentication

interface AuthenticationCache {

    fun fetchCurrentRequestToken(): String

    fun fetchCurrentSessionId(): Int

    fun saveCurrentRequestToken(requestToken: String)

    fun saveCurrentSessionId(sessionId: Int)

}