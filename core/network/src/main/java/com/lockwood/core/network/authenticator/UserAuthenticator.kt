package com.lockwood.core.network.authenticator

import com.lockwood.core.network.common.Constants.AUTH_HEADER
import com.lockwood.core.network.common.Constants.AUTH_HEADER_VALUE_PREFIX
import com.lockwood.core.network.TokensRepository
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class UserAuthenticator @Inject constructor(
    private val tokensRepository: TokensRepository
) : Authenticator {

    private val currentAccessToken
        get() = tokensRepository.getAccessToken()

    @Synchronized
    override fun authenticate(route: Route?, response: Response): Request? {
        val currentToken = currentAccessToken
        val requestToken = response.getAccessToken()

        return when {
            // Пропускаеи обновление токена если нет токена ¯\_(ツ)_/¯
            currentToken.isEmpty() -> {
                null
            }
            // Обновляем токен для запроса со старым токеном
            currentToken == requestToken -> {
                refreshToken()
                val newAccessToken = currentAccessToken
                buildRequestWithNewAccessToken(response, newAccessToken)
            }
            // Переписываем токен у запроса
            else -> {
                buildRequestWithNewAccessToken(response, currentToken)
            }
        }
    }

    private fun Response.getAccessToken(): String {
        return ""
    }

    // Переписываем токен у запроса
    private fun buildRequestWithNewAccessToken(
        response: Response,
        newAccessToken: String
    ): Request {
        return response.request()
            .newBuilder()
            .header(AUTH_HEADER, "$AUTH_HEADER_VALUE_PREFIX$newAccessToken")
            .build()
    }

    @Synchronized
    private fun refreshToken() {
        // Refresh tokens here
    }

}