package com.lockwood.test.extensions

import okhttp3.mockwebserver.MockResponse
import java.net.HttpURLConnection

private const val RESPONSE_BODY_INVALID_API_KEY =
    "{\n" +
            "  \"status_message\": \"Invalid API key: You must be granted a valid key.\",\n" +
            "  \"success\": false,\n" +
            "  \"status_code\": 7\n" +
            "}"

private const val RESPONSE_BODY_NOT_FOUND =
    "{\n" +
            "  \"status_message\": \"The resource you requested could not be found.\",\n" +
            "  \"status_code\": 34\n" +
            "}"

private const val RESPONSE_BODY_INVALID_CREDENTIALS =
    "{\n" +
            "  \"status_code\": 30,\n" +
            "  \"status_message\": \"Invalid username and/or password: You did not provide a valid login.\"\n" +
            "}"

fun MockResponse.successResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_OK)
}

fun MockResponse.badRequestResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
}

fun MockResponse.invalidApiKeyResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
        .setBody(RESPONSE_BODY_INVALID_API_KEY)
}

fun MockResponse.notFoundResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        .setBody(RESPONSE_BODY_NOT_FOUND)
}

fun MockResponse.invalidCredentialsResponse(): MockResponse {
    return setResponseCode(30)
        .setBody(RESPONSE_BODY_INVALID_CREDENTIALS)
}