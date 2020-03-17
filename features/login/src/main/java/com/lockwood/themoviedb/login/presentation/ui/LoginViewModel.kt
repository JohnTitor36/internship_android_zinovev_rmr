package com.lockwood.themoviedb.login.presentation.ui

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.Event
import com.lockwood.core.extensions.invoke
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.network.exception.NoInternetConnectionException
import com.lockwood.core.network.extensions.isHasInternetConnection
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.schedulers.AndroidSchedulersProvider
import com.lockwood.core.ui.BaseViewModel
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.lockwood.themoviedb.login.utils.CredentialsLengthValidator
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

    val openNextActivityEvent = MutableLiveData<Event<Unit>>()

    val noInternetConnectionEvent = MutableLiveData<Event<Unit>>()

    private val login: String
        get() = loginLiveData.value.orEmpty().trim()

    private val password: String
        get() = passwordLiveData.value.orEmpty().trim()

    fun setLogin(login: String) {
        loginLiveData.value = login
    }

    fun setPassword(password: String) {
        passwordLiveData.value = password
    }

    fun checkIsValidCredentialsLength() {
        val isValidLength = CredentialsLengthValidator.validateLength(login, password)
        isCredentialsLengthValid.value = isValidLength
    }

    fun login() {
        if (context.isHasInternetConnection) {
            setIsLoading(true)
            val loginBody = ValidateWithLoginBody(login, password, "")
            authenticationRepository.validateTokenWithLogin(apiKey, loginBody)
                .schedulersIoToMain(schedulers)
                .subscribe({
                    setIsLoading(false)
                    errorMessageLiveData.value = null
                    userPreferences.setUserLoggedIn(true)
                    openNextActivityEvent.invoke()
                }, { throwable ->
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
                }).autoDispose()
        } else {
            noInternetConnectionEvent.invoke()
        }
    }

}