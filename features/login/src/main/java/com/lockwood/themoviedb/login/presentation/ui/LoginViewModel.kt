package com.lockwood.themoviedb.login.presentation.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.Event
import com.lockwood.core.extensions.invoke
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.network.exception.NoInternetConnectionException
import com.lockwood.core.network.extensions.hasInternetConnection
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.schedulers.AndroidSchedulersProvider
import com.lockwood.core.ui.BaseViewModel
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.domain.model.CreateRequestTokenResponse
import com.lockwood.themoviedb.login.domain.model.CreateSessionBody
import com.lockwood.themoviedb.login.domain.model.CreateSessionResponse
import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.lockwood.themoviedb.login.utils.CredentialsValidator
import io.reactivex.Completable
import javax.inject.Inject

class LoginViewModel @Inject
constructor(
    @ApiKey private val apiKey: String,
    private val context: Context,
    private val authenticationRepository: AuthenticationRepository,
    private val userPreferences: UserPreferences,
    private val schedulers: AndroidSchedulersProvider
) : BaseViewModel() {

    val loginLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }
    val passwordLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val errorMessageLiveData: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    val isCredentialsLengthValid: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val keyboardOpened: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val openNextActivityEvent = MutableLiveData<Event<Unit>>()

    val noInternetConnectionEvent = MutableLiveData<Event<Unit>>()

    private val login: String
        get() = loginLiveData.value.orEmpty().trim()

    private val password: String
        get() = passwordLiveData.value.orEmpty().trim()

    private var requestToken: String
        get() = authenticationRepository.fetchCurrentRequestToken()
        set(value) {
            authenticationRepository.saveCurrentRequestToken(value)
        }

    private var sessionId: String
        get() = authenticationRepository.fetchCurrentSessionId()
        set(value) {
            authenticationRepository.saveCurrentSessionId(value)
        }

    fun setLogin(login: String) {
        loginLiveData.value = login
    }

    fun setPassword(password: String) {
        passwordLiveData.value = password
    }

    fun checkIsValidCredentialsLength() {
        val isValidLength = CredentialsValidator.isValidLength(login, password)
        isCredentialsLengthValid.value = isValidLength
    }

    fun login() {
        if (context.hasInternetConnection) {
            createRequestToken().schedulersIoToMain(schedulers)
                .doOnSubscribe { setIsLoading(true) }
                .subscribe(
                    { createSessionWithToken() },
                    { handleFailedLogin(it) }
                )
                .autoDispose()
        } else {
            noInternetConnectionEvent.invoke()
        }
    }

    private fun createSessionWithToken() {
        validateRequestToken()
            .andThen(createSession())
            .schedulersIoToMain(schedulers)
            .subscribe(
                { handleSuccessLogin() },
                { handleFailedLogin(it) }
            )
            .autoDispose()
    }

    private fun createRequestToken(): Completable {
        return Completable.fromSingle(
            authenticationRepository.createRequestToken(apiKey)
                .doOnSuccess { response: CreateRequestTokenResponse ->
                    requestToken = response.requestToken
                }
        )
    }

    private fun validateRequestToken(): Completable {
        val loginBody = ValidateWithLoginBody(login, password, requestToken)
        return authenticationRepository.validateTokenWithLogin(apiKey, loginBody)
    }

    private fun createSession(): Completable {
        val createSessionBody = CreateSessionBody(requestToken)
        return Completable.fromSingle(
            authenticationRepository.createSession(apiKey, createSessionBody)
                .doOnSuccess { response: CreateSessionResponse ->
                    sessionId = response.sessionId
                }
        )
    }

    private fun handleSuccessLogin() {
        setIsLoading(false)
        errorMessageLiveData.value = null
        userPreferences.setUserLoggedIn(true)
        openNextActivityEvent.invoke()
    }

    private fun handleFailedLogin(throwable: Throwable) {
        setIsLoading(false)
        if (throwable is NoInternetConnectionException) {
            noInternetConnectionEvent.invoke()
        } else {
            val message = throwable.message!!
            val engInvalidCredentials =
                context.getString(R.string.title_eng_invalid_username_or_password)
            val ruInvalidCredentials =
                context.getString(R.string.title_invalid_username_or_password)
            errorMessageLiveData.value =
                if (message.contains(Regex(engInvalidCredentials))) {
                    ruInvalidCredentials
                } else {
                    message
                }
        }
    }

}