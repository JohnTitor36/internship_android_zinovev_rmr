package com.lockwood.core.network.authenticator

import android.content.Context
import com.lockwood.core.network.exception.StatusMessageException
import com.lockwood.core.network.extensions.parseStatusMessage
import com.squareup.moshi.Moshi
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route

class StatusMessageAuthenticator(
    private val context: Context,
    private val moshi: Moshi
) : Authenticator {

    @Synchronized
    override fun authenticate(route: Route?, response: Response): Request? {
        throw StatusMessageException(context, moshi.parseStatusMessage(response))
    }

}