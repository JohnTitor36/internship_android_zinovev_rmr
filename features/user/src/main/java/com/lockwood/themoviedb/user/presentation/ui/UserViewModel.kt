package com.lockwood.themoviedb.user.presentation.ui

import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.preferences.di.qualifier.SessionId
import com.lockwood.core.router.LoginActivityRouter
import com.lockwood.core.schedulers.AndroidSchedulersProvider
import com.lockwood.core.ui.BaseViewModel
import com.lockwood.themoviedb.user.remote.AccountService
import com.lockwood.themoviedb.user.remote.model.body.DeleteSessionBodyModel
import timber.log.Timber
import javax.inject.Inject

class UserViewModel @Inject constructor(
    @ApiKey private val apiKey: String,
    @SessionId private val sessionId: String,
    private val accountService: AccountService,
    private val schedulers: AndroidSchedulersProvider,
    private val loginActivityRouter: LoginActivityRouter
) : BaseViewModel() {

    fun logout() {
        // TODO: Перейти на clean architecture в user
        val sessionBodyModel = DeleteSessionBodyModel(sessionId)
        accountService.deleteSession(apiKey, sessionBodyModel)
            .schedulersIoToMain(schedulers)
            .subscribe(
                { loginActivityRouter.openLoginActivity() },
                { e -> Timber.e(e) }
            ).autoDispose()
    }

    fun fetchAccountDetails() {
//        Для проверки токена пробуем получить информацию об аккаунте
//        accountService.getAccountDetails(sessionId, apiKey)
        accountService.getAccountDetails(apiKey, sessionId)
            .schedulersIoToMain(schedulers)
            .subscribe(
                { accountResponse -> Timber.d("account: ${accountResponse.id}") },
                { e -> Timber.e(e) }
            ).autoDispose()
    }

}