package com.lockwood.themoviedb.login

import com.lockwood.test.extensions.notFoundResponse
import com.lockwood.test.extensions.successResponse
import com.lockwood.themoviedb.login.ResponseConstants.REQUEST_TOKEN
import com.lockwood.themoviedb.login.ResponseConstants.SESSION_ID
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

        private const val RESPONSE_AUTHENTICATION_NEW_TOKEN =
            "{\n" +
                    "  \"success\": true,\n" +
                    "  \"expires_at\": \"2016-08-26 17:04:39 UTC\",\n" +
                    "  \"request_token\": \"$REQUEST_TOKEN\"\n" +
                    "}"
        private const val RESPONSE_AUTHENTICATION_TOKEN_VALIDATE_WITH_LOGIN =
            "{\n" +
                    "  \"success\": true,\n" +
                    "  \"expires_at\": \"2018-07-24 04:10:26 UTC\",\n" +
                    "  \"request_token\": \"$REQUEST_TOKEN\"\n" +
                    "}"
        private const val RESPONSE_AUTHENTICATION_SESSION_NEW =
            "{\n" +
                    "  \"success\": true,\n" +
                    "  \"session_id\": \"$SESSION_ID\"\n" +
                    "}"
    }

    override fun dispatch(request: RecordedRequest): MockResponse {
        return when (request.path) {
            PATH_AUTHENTICATION_NEW_TOKEN -> {
                MockResponse().successResponse()
                    .setBody(RESPONSE_AUTHENTICATION_NEW_TOKEN)
            }
            PATH_AUTHENTICATION_TOKEN_VALIDATE_WITH_LOGIN -> {
                MockResponse().successResponse()
                    .setBody(RESPONSE_AUTHENTICATION_TOKEN_VALIDATE_WITH_LOGIN)
            }
            PATH_AUTHENTICATION_SESSION_NEW -> {
                MockResponse().successResponse()
                    .setBody(RESPONSE_AUTHENTICATION_SESSION_NEW)
            }
            else -> MockResponse().notFoundResponse()
        }
    }

}