package com.lockwood.core.network.interceptor

import com.lockwood.core.network.common.RequestConstants.CONTENT_TYPE_HEADER
import com.lockwood.core.network.common.RequestConstants.HEADER_CONTENT_TYPE_JSON
import okhttp3.Interceptor
import okhttp3.Response

class OkHttpHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .addHeader(CONTENT_TYPE_HEADER, HEADER_CONTENT_TYPE_JSON).build()
        return chain.proceed(request)
    }

}