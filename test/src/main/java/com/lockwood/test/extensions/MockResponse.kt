package com.lockwood.test.extensions

import okhttp3.mockwebserver.MockResponse
import java.net.HttpURLConnection

private const val RESPONSE_BODY_INVALID_API_KEY =
    "{\"status_message\":\"Invalid API key: You must be granted a valid key.\",\"success\":false,\"status_code\":7}"

private const val RESPONSE_BODY_NOT_FOUND =
    "{\"status_message\":\"The resource you requested could not be found.\",\"status_code\":34}"


fun MockResponse.successResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_OK)
}

fun MockResponse.badRequestResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_BAD_REQUEST)
}

fun MockResponse.unauthorizedResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
}

fun MockResponse.invalidApiKeyResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
        .setBody(RESPONSE_BODY_INVALID_API_KEY)
}

fun MockResponse.notFoundResponse(): MockResponse {
    return setResponseCode(HttpURLConnection.HTTP_NOT_FOUND)
        .setBody(RESPONSE_BODY_NOT_FOUND)
}

fun String.withKey(apiKey: String = ""): String {
    return "$this?api_key=$apiKey"
}