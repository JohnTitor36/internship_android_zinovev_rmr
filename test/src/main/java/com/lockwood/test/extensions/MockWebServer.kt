package com.lockwood.test.extensions

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

private const val BASE_URL = "https://api.themoviedb.org/3/"

val mockServer = MockWebServer().apply { start() }

val mockWebServerUrl = mockServer.url(BASE_URL).toString()

val notFoundDispatcher = object : Dispatcher() {

    override fun dispatch(request: RecordedRequest): MockResponse {
        return MockResponse().notFoundResponse()
    }
}