package com.lockwood.themoviedb.user.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.preferences.di.qualifier.SessionId
import com.lockwood.core.router.LoginActivityRouter
import com.lockwood.core.schedulers.AndroidSchedulersProvider
import com.lockwood.core.ui.BaseViewModel
import com.lockwood.core.window.WindowManager
import com.lockwood.themoviedb.user.remote.AccountService
import com.lockwood.themoviedb.user.remote.model.body.DeleteSessionBodyModel
import timber.log.Timber
import javax.inject.Inject

data class UserViewState(
    val username: String,
    val image: String
)

class UserViewModel @Inject constructor(
    @ApiKey private val apiKey: String,
    @SessionId private val sessionId: String,
    private val accountService: AccountService,
    private val schedulers: AndroidSchedulersProvider,
    private val loginActivityRouter: LoginActivityRouter,
    private val windowManager: WindowManager
) : BaseViewModel() {

    companion object {

        private const val GRAVATAR_IMAGE_BASE_URL = "https://www.gravatar.com/avatar/"
    }

    val liveState: MutableLiveData<UserViewState> = MutableLiveData(createInitialState())
    private var state: UserViewState by liveState.delegate()

    // TODO: Добавить информацию о пользователе в префы
    private fun createInitialState(): UserViewState {
        return UserViewState("", "")
    }

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
        accountService.getAccountDetails(apiKey, sessionId)
            .schedulersIoToMain(schedulers)
            .subscribe(
                { accountResponse ->
                    state = state.copy(
                        username = accountResponse.username,
                        image = gravatarImageUrl(accountResponse.avatar.gravatar.hash)
                    )
                },
                { e -> Timber.e(e) }
            ).autoDispose()
    }

    private fun gravatarImageUrl(hash: String): String {
        val imageSize = windowManager.screenWidth / 4
        return "$GRAVATAR_IMAGE_BASE_URL$hash?s=$imageSize"
    }

}