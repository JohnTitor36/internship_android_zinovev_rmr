package com.lockwood.themoviedb.login.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.domain.AndroidSchedulersProvider
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.toaster.Toaster
import com.lockwood.core.ui.BaseViewModel
import com.lockwood.themoviedb.login.common.CredentialsLengthValidator
import com.lockwood.themoviedb.login.data.repository.AuthRemoteRepository
import com.lockwood.themoviedb.login.presentation.model.Credentials
import javax.inject.Inject

class LoginViewModel @Inject
constructor(
    private val credentialsLengthValidator: CredentialsLengthValidator,
    private val authRepository: AuthRemoteRepository,
    private val schedulers: AndroidSchedulersProvider,
    private val toaster: Toaster
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
        val isValidLength = credentialsLengthValidator.validateLength(login, password)
        isCredentialsLengthValid.value = isValidLength
    }

    // TODO: Убрать заглушки
    fun login() {
        authRepository.authUser(Credentials(login, password))
            .schedulersIoToMain(schedulers)
            .subscribe({
                setIsLoading(false)
                errorMessageLiveData.value = null
                toaster.toast("Вы успешно вошли")
            }, {
                setIsLoading(false)
                errorMessageLiveData.value = it.message
            }).autoDispose()
    }

}