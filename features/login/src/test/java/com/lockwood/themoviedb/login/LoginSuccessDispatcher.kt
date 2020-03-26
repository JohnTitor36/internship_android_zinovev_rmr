package com.lockwood.themoviedb.login

import com.lockwood.test.extensions.notFoundResponse
import com.lockwood.test.extensions.successResponse
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

internal class LoginSuccessDispatcher : Dispatcher() {

    companion object {

        private const val PATH_AUTHENTICATION_NEW_TOKEN =
            "authentication/token/new"
        private const val PATH_AUTHENTICATION_TOKEN_VALIDATE_WITH_LOGIN =
            "authentication/token/validate_with_login"
        private const val PATH_AUTHENTICATION_SESSION_NEW =
            "authentication/session/new"
    }

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            PATH_AUTHENTICATION_NEW_TOKEN -> MockResponse().successResponse()
            PATH_AUTHENTICATION_TOKEN_VALIDATE_WITH_LOGIN -> MockResponse().successResponse()
            PATH_AUTHENTICATION_SESSION_NEW -> MockResponse().successResponse()
            else -> MockResponse().notFoundResponse()
        }
    }

}