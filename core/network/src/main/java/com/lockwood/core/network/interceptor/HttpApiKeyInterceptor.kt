package com.lockwood.core.network.interceptor

import com.lockwood.core.network.extensions.editUrl
import okhttp3.Interceptor
import okhttp3.Response

class HttpApiKeyInterceptor(
    private val apiKey: String
) : Interceptor {

    companion object {

        private const val APY_KEY_FIELD = "api_key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().editUrl {
            addQueryParameter(APY_KEY_FIELD, apiKey)
        }
        return chain.proceed(request)
    }

}