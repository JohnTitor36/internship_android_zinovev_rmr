package com.lockwood.themoviedb.login.extensions

import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.reader.ResourceReader
import com.lockwood.core.schedulers.SchedulersProvider
import com.lockwood.test.schedulers.TestSchedulersProvider
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.lockwood.themoviedb.login.presentation.ui.LoginViewModel
import com.nhaarman.mockitokotlin2.mock

fun createLoginViewModel(
    apiKey: String,
    authenticationRepository: AuthenticationRepository,
    schedulers: SchedulersProvider = TestSchedulersProvider(),
    userPreferences: UserPreferences = mock(),
    resourceReader: ResourceReader = mock(),
    connectivityManager: NetworkConnectivityManager = mock()
): LoginViewModel {
    return LoginViewModel(
        apiKey = apiKey,
        authenticationRepository = authenticationRepository,
        userPreferences = userPreferences,
        resourceReader = resourceReader,
        connectivityManager = connectivityManager,
        schedulers = schedulers
    )
}