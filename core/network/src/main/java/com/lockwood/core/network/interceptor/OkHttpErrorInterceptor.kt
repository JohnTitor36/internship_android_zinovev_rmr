package com.lockwood.core.network.interceptor

import android.content.Context
import com.lockwood.core.network.exception.NoInternetConnectionException
import com.lockwood.core.network.exception.StatusMessageException
import com.lockwood.core.network.extensions.hasInternetConnection
import com.lockwood.core.network.extensions.parseStatusMessage
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response

class OkHttpErrorInterceptor(
    private val context: Context,
    private val moshi: Moshi
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.hasInternetConnection) {
            throw NoInternetConnectionException()
        }
        val response = chain.proceed(chain.request())

        if (!response.isSuccessful) {
            val statusMessage = moshi.parseStatusMessage(response)
            if (statusMessage != null) {
                throw StatusMessageException(statusMessage)
            } else {
                return response
            }
        }
        return response
    }

}