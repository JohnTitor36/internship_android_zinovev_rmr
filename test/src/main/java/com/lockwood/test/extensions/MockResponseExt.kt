package com.lockwood.test.extensions

import okhttp3.mockwebserver.MockResponse
import java.net.HttpURLConnection

private const val DEFAULT_RESPONSE_BODY_UNAUTHORIZED =
    "{\n" +
            "  \"status_message\": \"Invalid API key: You must be granted a valid key.\",\n" +
            "  \"success\": false,\n" +
            "  \"status_code\": 7\n" +
            "}"

private const val DEFAULT_RESPONSE_BODY_NOT_FOUND =
    "{\n" +
            "  \"status_message\": \"The resource you requested could not be found.\",\n" +
            "  \"status_code\": 34\n" +
            "}"

fun MockResponse.successResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_OK)
}

fun MockResponse.badRequestResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
}

fun MockResponse.unauthorizedResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
        .setBody(DEFAULT_RESPONSE_BODY_UNAUTHORIZED)
}

fun MockResponse.notFoundResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        .setBody(DEFAULT_RESPONSE_BODY_NOT_FOUND)
}