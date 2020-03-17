package com.lockwood.core.network.interceptor

import android.content.Context
import com.lockwood.core.network.exception.NoInternetConnectionException
import com.lockwood.core.network.exception.StatusMessageException
import com.lockwood.core.network.extensions.isHasInternetConnection
import com.lockwood.core.network.extensions.parseStatusMessage
import com.squareup.moshi.Moshi
import okhttp3.Interceptor
import okhttp3.Response
import javax.net.ssl.HttpsURLConnection

class OkHttpErrorInterceptor(
    private val context: Context,
    private val moshi: Moshi
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!context.isHasInternetConnection) {
            throw NoInternetConnectionException(context)
        }
        val request = chain.request()
        val response = chain.proceed(request)
        val code = response.code

        // 401 ловим в Authenticator
        // Из 404 ловим status_message
        if (code != HttpsURLConnection.HTTP_UNAUTHORIZED && code == HttpsURLConnection.HTTP_NOT_FOUND) {
            throw StatusMessageException(context, moshi.parseStatusMessage(response))
        }
        return response
    }

}