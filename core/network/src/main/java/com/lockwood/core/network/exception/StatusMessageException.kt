package com.lockwood.core.network.exception

import java.io.IOException

class StatusMessageException(statusMessage: String) : IOException() {

    override val message = statusMessage

}