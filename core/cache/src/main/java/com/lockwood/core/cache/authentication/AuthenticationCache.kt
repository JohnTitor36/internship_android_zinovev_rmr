package com.lockwood.core.cache.authentication

interface AuthenticationCache {

    fun fetchCurrentRequestToken(): String

    fun fetchCurrentSessionId(): Int

    fun saveCurrentRequestToken(requestToken: String)

    fun saveCurrentSessionId(sessionId: Int)

}