package com.lockwood.core.network.interceptor

import com.lockwood.core.network.common.RequestConstants.CONTENT_TYPE_JSON
import com.lockwood.core.network.common.RequestConstants.HEADER_CONTENT_TYPE
import okhttp3.Interceptor
import okhttp3.Response

class OkHttpHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
            .header(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON).build()
        return chain.proceed(request)
    }

}