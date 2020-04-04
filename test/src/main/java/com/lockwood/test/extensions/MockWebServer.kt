package com.lockwood.test.extensions

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

const val BASE_URL = "https://api.themoviedb.org/3/"

const val CREATE_REQUEST_TOKEN = "authentication/token/new"

const val VALIDATE_TOKEN_WITH_LOGIN_PATH = "authentication/token/validate_with_login"

const val CREATE_SESSION_PATH = "authentication/session/new"

inline fun MockWebServer.dispatchResponses(
    crossinline onDispatch: (String?) -> MockResponse?
) {
    val dispatcherForResponses = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            return onDispatch(request.path) ?: MockResponse()
        }
    }
    dispatcher = dispatcherForResponses
}