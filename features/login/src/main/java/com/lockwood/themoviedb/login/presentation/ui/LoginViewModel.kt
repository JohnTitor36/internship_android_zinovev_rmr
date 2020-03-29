package com.lockwood.themoviedb.login.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.Event
import com.lockwood.core.extensions.delegate
import com.lockwood.core.extensions.invoke
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.network.exception.NoInternetConnectionException
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
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
    private val resourceReader: ResourceReader,
    private val connectivityManager: NetworkConnectivityManager,
    private val authenticationRepository: AuthenticationRepository,
    private val userPreferences: UserPreferences,
    private val schedulers: SchedulersProvider
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

    val keyboardOpenedLiveData: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>()
    }

    val openNextActivityEvent by lazy {
        MutableLiveData<Event<Unit>>()
    }

    val noInternetConnectionEvent by lazy {
        MutableLiveData<Event<Unit>>()
    }

    var login: String
        get() = loginLiveData.value.orEmpty().trim()
        set(value) {
            loginLiveData.value = value
        }

    var password: String
        get() = passwordLiveData.value.orEmpty().trim()
        set(value) {
            passwordLiveData.value = value
        }

    var keyboardOpened by keyboardOpenedLiveData.delegate()

    private var errorMessage by errorMessageLiveData.delegate()

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

    fun checkIsValidCredentialsLength() {
        val isValidLength = CredentialsValidator.isValidLength(login, password)
        isCredentialsLengthValid.value = isValidLength
    }

    fun login() {
        if (connectivityManager.hasInternetConnection) {
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
                resourceReader.string(R.string.title_eng_invalid_username_or_password)
            val ruInvalidCredentials =
                resourceReader.string(R.string.title_invalid_username_or_password)
            errorMessage = if (message.contains(Regex(engInvalidCredentials))) {
                ruInvalidCredentials
            } else {
                message
            }
        }
    }

}