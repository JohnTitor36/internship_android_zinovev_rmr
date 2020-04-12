package com.lockwood.themoviedb.login.extensions

import com.lockwood.test.extensions.successResponse
import com.lockwood.test.extensions.unauthorizedResponse
import okhttp3.mockwebserver.MockResponse

private const val BODY_SUCCESS_CREATE_REQUEST_TOKEN =
    "{\"success\":true,\"expires_at\":\"2020-04-12 14:02:55 UTC\",\"request_token\":\"6030c52add5c9a2e9fff8a75d8a81f284163b038\"}"

private const val BODY_SUCCESS_VALIDATE_TOKEN_WITH_LOGIN =
    BODY_SUCCESS_CREATE_REQUEST_TOKEN

private const val BODY_SUCCESS_CREATE_SESSION =
    "{\"success\":true,\"session_id\":\"7ad133d5e3d4bc26da57c4c73329833ec27d1f3e\"}"

private const val BODY_INVALID_CREDENTIALS =
    "{\"status_code\":30,\"status_message\":\"Invalid username and/or password: You did not provide a valid login.\"}"

fun MockResponse.successCreateRequestTokenResponse(): MockResponse {
    return successResponse().setBody(BODY_SUCCESS_CREATE_REQUEST_TOKEN)
}

fun MockResponse.successValidateTokenWithLoginResponse(): MockResponse {
    return successResponse().setBody(BODY_SUCCESS_VALIDATE_TOKEN_WITH_LOGIN)
}

fun MockResponse.successCreateSessionResponse(): MockResponse {
    return successResponse().setBody(BODY_SUCCESS_CREATE_SESSION)
}

fun MockResponse.invalidCredentialsResponse(): MockResponse {
    return unauthorizedResponse().setBody(BODY_INVALID_CREDENTIALS)
}
