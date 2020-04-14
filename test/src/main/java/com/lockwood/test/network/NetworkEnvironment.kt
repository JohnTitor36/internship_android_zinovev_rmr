package com.lockwood.test.network

import com.squareup.moshi.Moshi
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

interface MockServer {

    fun dispatchResponses(onDispatch: (String) -> MockResponse?)

    fun dispatchResponse(path: String): MockResponse

    fun setupServer()

    fun shutdownServer()
}

interface NetworkEnvironmentMaker {

    fun createOkHttpClient(
        interceptors: Array<Interceptor>,
        authenticator: Authenticator? = null
    ): OkHttpClient

    fun createRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit

}

abstract class NetworkEnvironment : MockServer, NetworkEnvironmentMaker {

    companion object {

        private const val BASE_URL = "/"

        private const val DEFAULT_TIMEOUT_SECONDS = 30L
    }

    protected val httpClientBuilder
        get() = OkHttpClient().newBuilder()
            .connectTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_TIMEOUT_SECONDS, TimeUnit.SECONDS)

    protected val retrofitBuilder
        get() = Retrofit.Builder()

    protected val moshiBuilder
        get() = Moshi.Builder()

    private val mockServer = MockWebServer().apply { start() }

    private val baseUrl
        get() = mockServer.url(BASE_URL).toString()

    override fun shutdownServer() {
        mockServer.shutdown()
    }

    override fun dispatchResponses(
        onDispatch: (String) -> MockResponse?
    ) {
        mockServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                val path = requireNotNull(request.path)
                return onDispatch(path) ?: dispatchResponse(path)
            }
        }
    }

    override fun createOkHttpClient(
        interceptors: Array<Interceptor>,
        authenticator: Authenticator?
    ): OkHttpClient {
        val okHttpClient = httpClientBuilder
        interceptors.forEach { interceptor ->
            okHttpClient.addInterceptor(interceptor)
        }
        if (authenticator != null) {
            okHttpClient.authenticator(authenticator)
        }
        return okHttpClient.build()
    }

    override fun createRetrofit(
        okHttpClient: OkHttpClient,
        moshi: Moshi
    ): Retrofit {
        return retrofitBuilder.baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    inline fun <reified T : Any> createService(retrofit: Retrofit): T {
        return retrofit.create(T::class.java)
    }

}