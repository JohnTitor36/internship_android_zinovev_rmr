package com.lockwood.core.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HttpApiKeyInterceptor(
    private val apiKey: String
) : Interceptor {

    companion object {

        private const val APY_KEY_FIELD = "api_key"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val url = original.url.newBuilder()
            .addQueryParameter(APY_KEY_FIELD, apiKey)
            .build()

        val request = original.newBuilder()
            .url(url)
            .build()
        return chain.proceed(request)
    }

}