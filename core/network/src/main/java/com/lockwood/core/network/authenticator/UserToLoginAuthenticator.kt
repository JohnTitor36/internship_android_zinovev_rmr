package com.lockwood.core.network.authenticator

import com.lockwood.core.network.router.LoginActivityRouter
import com.lockwood.core.network.validator.ApiValuesValidator
import com.lockwood.core.preferences.user.UserPreferences
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class UserToLoginAuthenticator @Inject constructor(
    private val userPreferences: UserPreferences,
    private val apiValuesValidator: ApiValuesValidator,
    private val loginActivityRouter: LoginActivityRouter
) : Authenticator {

    private val isValidToken: Boolean
        get() = apiValuesValidator.isValidToken

    private val isValidSessionId: Boolean
        get() = apiValuesValidator.isValidSessionId

    // В случае истечения срока request_token или session_id, необходимо открыть экран
    // авторизации для повторного получения токена
    @Synchronized
    override fun authenticate(route: Route?, response: Response): Request? {
        if (isValidToken && isValidSessionId) {
            apiValuesValidator.resetApiValues()
            userPreferences.setUserLoggedIn(false)
            loginActivityRouter.openLoginActivity()
        }
        return null
    }

}