package com.lockwood.core.network.interceptor

import com.lockwood.core.network.exception.NoInternetConnectionException
import com.lockwood.core.network.exception.StatusMessageException
import com.lockwood.core.network.extensions.parseStatusMessage
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response

class OkHttpErrorInterceptor(
    private val connectivityManager: NetworkConnectivityManager,
    private val moshi: Moshi
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!connectivityManager.hasInternetConnection) {
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