package com.lockwood.themoviedb.login

import com.lockwood.core.network.interceptor.OkHttpErrorInterceptor
import com.lockwood.core.network.interceptor.OkHttpHeaderInterceptor
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.reader.ResourceReader
import com.lockwood.test.extensions.notFoundResponse
import com.lockwood.test.extensions.withKey
import com.lockwood.test.network.NetworkEnvironment
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.lockwood.themoviedb.login.extensions.*
import com.lockwood.themoviedb.login.remote.AuthenticationService
import com.nhaarman.mockitokotlin2.mock
import okhttp3.mockwebserver.MockResponse

class AuthenticationNetworkEnvironment : NetworkEnvironment() {

    companion object {

        const val DEFAULT_API_KEY = "79191836ddaa0da3df76a5ffef6f07ad6ab0c641"
        const val DEFAULT_REQUEST_TOKEN = "ff5c7eeb5a8870efe3cd7fc5c282cffd26800ecd"
    }

    var apiKey: String =
        DEFAULT_API_KEY

    val mockResourceReader = mock<ResourceReader>()
    val mockConnectivityManager = mock<NetworkConnectivityManager>()

    lateinit var authenticationRepository: AuthenticationRepository

    fun setupServer(apiKey: String) {
        this.apiKey = apiKey
        setupServer()
    }

    override fun setupServer() {
        val moshi = moshiBuilder.build()

        val interceptors = arrayOf(
            OkHttpErrorInterceptor(mockConnectivityManager, moshi),
            OkHttpHeaderInterceptor()
        )
        val httpClient = createOkHttpClient(interceptors)
        val retrofit = createRetrofit(httpClient, moshi)

        val service = createService<AuthenticationService>(retrofit)

        val remote = createAuthenticationRemote(service)
        val remoteDataSource = createAuthenticationRemoteDataSource(remote)
        authenticationRepository = createAuthenticationRepository(remoteDataSource)
    }

    override fun dispatchResponse(path: String): MockResponse {
        return when (path) {
            CREATE_REQUEST_TOKEN_PATH.withKey(apiKey) -> MockResponse().successCreateRequestTokenResponse()
            VALIDATE_TOKEN_WITH_LOGIN_PATH.withKey(apiKey) -> MockResponse().successValidateTokenWithLoginResponse()
            CREATE_SESSION_PATH.withKey(apiKey) -> MockResponse().successCreateSessionResponse()
            else -> MockResponse().notFoundResponse()
        }
    }

}