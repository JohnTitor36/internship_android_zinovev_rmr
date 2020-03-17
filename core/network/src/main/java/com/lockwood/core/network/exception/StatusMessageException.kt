package com.lockwood.core.network.exception

import android.content.Context
import com.lockwood.core.network.R

class StatusMessageException(
    private val context: Context,
    private val statusMessage: String
) : Exception() {

    override val cause: Throwable
        get() = Throwable(context.getString(R.string.title_status_exception_cause))

    override val message: String
        get() = statusMessage

}