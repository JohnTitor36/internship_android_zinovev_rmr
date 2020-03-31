package com.lockwood.themoviedb.user.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.EventsQueue
import com.lockwood.core.event.MessageEvent
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.di.qualifier.ApiKey
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.preferences.di.qualifier.SessionId
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.router.LoginActivityRouter
import com.lockwood.core.schedulers.SchedulersProvider
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
    private val accountService: AccountService,
    private val loginActivityRouter: LoginActivityRouter,
    private val windowManager: WindowManager,
    @SessionId private val sessionId: String,
    @ApiKey apiKey: String,
    resourceReader: ResourceReader,
    connectivityManager: NetworkConnectivityManager,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(apiKey, resourceReader, connectivityManager, schedulers) {

    companion object {

        private const val GRAVATAR_IMAGE_BASE_URL = "https://www.gravatar.com/avatar/"
    }

    val liveState: MutableLiveData<UserViewState> = MutableLiveData(createInitialState())

    private var state: UserViewState by liveState.delegate()

    val eventsQueue by lazy { EventsQueue() }

    override fun handleError(throwable: Throwable) {
        if (throwable.isNoInternetException) {
            eventsQueue.offer(noInternetEvent)
        } else {
            val message = throwable.message.toString()
            val event = MessageEvent(message)
            eventsQueue.offer(event)
        }
    }

    fun logout() = checkHasInternet(
        onHasConnection = {
            val sessionBodyModel = DeleteSessionBodyModel(sessionId)
            accountService.deleteSession(apiKey, sessionBodyModel)
                .schedulersIoToMain(schedulers)
                .subscribe(
                    { loginActivityRouter.openLoginActivity() },
                    { e -> handleError(e) }
                ).autoDispose()
        },
        onNoConnection = { eventsQueue.offer(noInternetEvent) }
    )

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

    // TODO: Добавить информацию о пользователе в префы
    private fun createInitialState(): UserViewState {
        return UserViewState("", "")
    }

    private fun gravatarImageUrl(hash: String): String {
        val imageSize = windowManager.screenWidth / 4
        return "$GRAVATAR_IMAGE_BASE_URL$hash?s=$imageSize"
    }

}