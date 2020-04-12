package com.lockwood.themoviedb.login.presentation.ui

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.EventsQueue
import com.lockwood.core.event.LaunchActivityEvent
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.domain.model.CreateRequestTokenResponse
import com.lockwood.themoviedb.login.domain.model.CreateSessionBody
import com.lockwood.themoviedb.login.domain.model.CreateSessionResponse
import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.lockwood.themoviedb.login.utils.CredentialsValidator
import io.reactivex.Completable
import javax.inject.Inject

data class LoginViewState(
    val login: String,
    val password: String,
    val errorMessage: String,
    val loading: Boolean,
    val validCredentials: Boolean,
    val keyboardOpened: Boolean
)

class LoginViewModel @Inject
constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userPreferences: UserPreferences,
    @ApiKey apiKey: String,
    resourceReader: ResourceReader,
    connectivityManager: NetworkConnectivityManager,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(apiKey, resourceReader, connectivityManager, schedulers) {

    companion object {

        private const val MAIN_ACTIVITY_CLASS_NAME =
            "com.lockwood.themoviedb.presentation.ui.MainActivity"
    }

    val liveState: MutableLiveData<LoginViewState> = MutableLiveData(createInitialState())

    val eventsQueue by lazy { EventsQueue() }

    private var state: LoginViewState by liveState.delegate()

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

    override fun handleError(throwable: Throwable) {
        setLoading(false)
        if (throwable.isNoInternetException) {
            eventsQueue.offer(noInternetEvent)
        } else {
            val throwableMessage = throwable.message
            if (throwableMessage != null) {
                val isInvalidCredentialsMessage = isInvalidCredentialsMessage(throwableMessage)
                val message = if (isInvalidCredentialsMessage) {
                    resourceReader.string(R.string.title_invalid_credentials)
                } else {
                    throwableMessage
                }
                state = state.copy(errorMessage = message)
            }
        }
    }

    fun onCredentialsChanged(login: String, password: String) {
        val isValid = CredentialsValidator.isValidInput(login, password)
        state = state.copy(login = login, password = password, validCredentials = isValid)
    }

    fun onEnterButtonClick() {
        createRequestToken().schedulersIoToMain(schedulers)
            .doOnSubscribe { setLoading(true) }
            .subscribe(
                { createSessionWithToken() },
                { handleError(it) }
            )
            .autoDispose()
    }

    fun onKeyboardOpened(keyboardOpened: Boolean) {
        state = state.copy(keyboardOpened = keyboardOpened)
    }

    private fun setLoading(loading: Boolean) {
        state = state.copy(loading = loading)
    }

    private fun createInitialState(): LoginViewState {
        return LoginViewState("", "", "", false, false, false)
    }

    private fun createSessionWithToken() {
        validateRequestToken()
            .andThen(createSession())
            .schedulersIoToMain(schedulers)
            .subscribe(
                { handleSuccessLogin() },
                { handleError(it) }
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
        val loginBody = ValidateWithLoginBody(state.login, state.password, requestToken)
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
        setLoading(false)
        userPreferences.setUserLoggedIn(true)

        val clearFlags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val launchMainEvent = LaunchActivityEvent(MAIN_ACTIVITY_CLASS_NAME, clearFlags)
        eventsQueue.offer(launchMainEvent)
    }

    private fun isInvalidCredentialsMessage(message: String): Boolean {
        val invalidCredentials = resourceReader.string(R.string.title_eng_invalid_credentials)
        return message.contains(Regex(invalidCredentials))
    }

}