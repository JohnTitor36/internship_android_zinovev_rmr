package com.lockwood.themoviedb.login.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.Event
import com.lockwood.core.schedulers.AndroidSchedulersProvider
import com.lockwood.core.ui.BaseViewModel
import com.lockwood.themoviedb.login.utils.CredentialsLengthValidator
import javax.inject.Inject

class LoginViewModel @Inject
constructor(
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
//        authRepository.authUser(CredentialsUi(login, password))
//            .schedulersIoToMain(schedulers)
//            .subscribe({
//                setIsLoading(false)
//                errorMessageLiveData.value = null
//                // TODO: Поставить флаг, что пользователь вошел
//                openNextActivityEvent.value = Event(Unit)
//            }, {
//                setIsLoading(false)
//                errorMessageLiveData.value = it.message
//            }).autoDispose()
    }

}