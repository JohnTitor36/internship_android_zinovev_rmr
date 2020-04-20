package com.lockwood.core.network.interceptor

import com.lockwood.core.network.extensions.editUrl
import okhttp3.Interceptor
import okhttp3.Response

class HttpSessionInterceptor(
    private val sessionId: String
) : Interceptor {

    companion object {

        private const val SESSION_ID_FIELD = "session_id"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().editUrl {
            addQueryParameter(SESSION_ID_FIELD, sessionId)
        }
        return chain.proceed(request)
    }

}