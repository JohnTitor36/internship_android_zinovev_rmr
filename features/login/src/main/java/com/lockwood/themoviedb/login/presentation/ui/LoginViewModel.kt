package com.lockwood.themoviedb.login.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.MessageEvent
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.manager.ConnectivityManager
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.domain.model.CreateRequestTokenResponse
import com.lockwood.themoviedb.login.domain.model.CreateSessionBody
import com.lockwood.themoviedb.login.domain.model.CreateSessionResponse
import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.lockwood.themoviedb.login.utils.CredentialsValidator
import com.scottyab.rootbeer.RootBeer
import io.reactivex.Completable
import javax.inject.Inject

class LoginViewModel @Inject
constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val connectivityManager: ConnectivityManager,
    private val rootBeer: RootBeer,
    resourceReader: ResourceReader,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(resourceReader, schedulers) {

    companion object {

        private const val CHECK_ENVIRONMENT_DELAY = 1L
    }

    val liveState: MutableLiveData<LoginViewState> = MutableLiveData(LoginViewState.initialState)

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
        super.handleError(throwable)
        setLoading(false)
        if (throwable.isNoInternetException) {
            eventsQueue.offer(noInternetEvent)
        } else {
            val throwableMessage = throwable.message
            if (throwableMessage != null) {
                val message = if (throwableMessage.isInvalidCredentialsMessage()) {
                    resourceReader.string(R.string.title_invalid_credentials)
                } else {
                    throwableMessage
                }
                state = state.copy(errorMessage = message)
            }
        }
    }

    fun onCredentialsChanged(login: String, password: String) {
        val formattedLogin = login.trim()
        val formattedPassword = password.trim()
        val isValid = CredentialsValidator.isValidInput(formattedLogin, formattedPassword)
        state = state.copy(
            login = formattedLogin,
            password = formattedPassword,
            validCredentials = isValid
        )
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

    fun checkEnvironmentSecurity() {
        val untrustedEnvironmentMessage = resourceReader.string(R.string.untrusted_environment)
        val messageBuilder = StringBuilder(untrustedEnvironmentMessage)

        if (rootBeer.isRooted) {
            val messageUsedRoot = resourceReader.string(R.string.untrusted_device_root)
            messageBuilder.appendln(messageUsedRoot)
        }

        if (!connectivityManager.safeConnection) {
            val messageUsedNotSafeConnection = resourceReader.string(R.string.untrusted_wifi)
            messageBuilder.appendln(messageUsedNotSafeConnection)
        }

        val message = messageBuilder.toString()
        if (message != untrustedEnvironmentMessage) {
            val rootEvent = MessageEvent(message)
            eventsQueue.offer(rootEvent)
        }
    }

    fun showRequestLocationReasonMessage(){
        val message = resourceReader.string(R.string.untrusted_environment_reason)
        val rootEvent = MessageEvent(message)
        eventsQueue.offer(rootEvent)
    }

    private fun setLoading(loading: Boolean) {
        state = state.copy(loading = loading)
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
            authenticationRepository.createRequestToken()
                .doOnSuccess { response: CreateRequestTokenResponse ->
                    requestToken = response.requestToken
                }
        )
    }

    private fun validateRequestToken(): Completable {
        val loginBody = ValidateWithLoginBody(state.login, state.password, requestToken)
        return authenticationRepository.validateTokenWithLogin(loginBody)
    }

    private fun createSession(): Completable {
        val createSessionBody = CreateSessionBody(requestToken)
        return Completable.fromSingle(
            authenticationRepository.createSession(createSessionBody)
                .doOnSuccess { response: CreateSessionResponse ->
                    sessionId = response.sessionId
                }
        )
    }

    private fun handleSuccessLogin() {
        setLoading(false)

//        if (hasPinPassword) {
//            openPinMake()
//        } else {
//            openPinEnter()
//        }

//        openPinMake()
    }

    private fun openPinMake() {
        val direction = LoginFragmentDirections.openMakePin()
        navigateTo(direction)
    }

    private fun openPinEnter() {

    }

    private fun String.isInvalidCredentialsMessage(): Boolean {
        val invalidCredentials = resourceReader.string(R.string.title_eng_invalid_credentials)
        return contains(Regex(invalidCredentials))
    }

}