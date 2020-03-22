package com.lockwood.core.network.exception

import java.io.IOException

class NoInternetConnectionException : IOException() {

    override val message = "Нет подключения к интернету"

}