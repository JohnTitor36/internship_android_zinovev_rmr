package com.lockwood.themoviedb.user.presentation.ui

import androidx.lifecycle.MutableLiveData
import com.lockwood.core.event.MessageEvent
import com.lockwood.core.extensions.schedulersIoToMain
import com.lockwood.core.livedata.delegate
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.network.ui.BaseNetworkViewModel
import com.lockwood.core.preferences.di.qualifier.SessionId
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.router.LoginActivityRouter
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.themoviedb.user.domain.model.DeleteSessionBody
import com.lockwood.themoviedb.user.domain.repository.AccountRepository
import timber.log.Timber
import javax.inject.Inject

// TODO: Добавить информацию о пользователе в префы
class UserViewModel @Inject constructor(
    private val accountRepository: AccountRepository,
    private val loginActivityRouter: LoginActivityRouter,
    @SessionId private val sessionId: String,
    resourceReader: ResourceReader,
    connectivityManager: NetworkConnectivityManager,
    schedulers: SchedulersProvider
) : BaseNetworkViewModel(resourceReader, connectivityManager, schedulers) {

    val liveState: MutableLiveData<UserViewState> = MutableLiveData(UserViewState.initialState)

    private var state: UserViewState by liveState.delegate()

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
            val sessionBodyModel = DeleteSessionBody(sessionId)
            accountRepository.deleteSession(sessionBodyModel)
                .schedulersIoToMain(schedulers)
                .subscribe(
                    { loginActivityRouter.openLoginActivity() },
                    { e -> handleError(e) }
                ).autoDispose()
        },
        onNoConnection = { eventsQueue.offer(noInternetEvent) }
    )

    fun fetchAccountDetails() {
        accountRepository.getAccountDetails(sessionId)
            .schedulersIoToMain(schedulers)
            .subscribe(
                { accountResponse ->
                    state = state.copy(
                        username = accountResponse.username,
                        image = accountResponse.gravatarUrl.value
                    )
                },
                { e -> Timber.e(e) }
            ).autoDispose()
    }

}