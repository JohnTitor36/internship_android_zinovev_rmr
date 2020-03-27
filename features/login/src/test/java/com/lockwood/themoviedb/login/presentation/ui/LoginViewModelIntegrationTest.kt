package com.lockwood.themoviedb.login.presentation.ui

import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.reader.ResourceReader
import com.lockwood.test.schedulers.TestSchedulersProvider
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.nhaarman.mockitokotlin2.mock
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object LoginViewModelIntegrationTest : Spek({

    Feature("Login") {

        //region Fields
        val apiKey: String by memoized {
            mock<String> {}
        }

        val resourceReader by memoized {
            mock<ResourceReader> {}
        }
        val connectivityManager by memoized {
            mock<NetworkConnectivityManager> {}
        }
        val authenticationRepository by memoized {
            mock<AuthenticationRepository> {}
        }
        val userPreferences by memoized {
            mock<UserPreferences> {}
        }
        val schedulers by memoized {
            TestSchedulersProvider()
        }

        val viewModel by memoized {
            mock<LoginViewModel> {
                LoginViewModel(
                    apiKey,
                    resourceReader,
                    connectivityManager,
                    authenticationRepository,
                    userPreferences,
                    schedulers
                )
            }
        }
        //endregion


        //region Lifecycle
        //endregion

        Scenario("success login") {

            Given("login with special characters and simple password") {
            }

            When("login") {
                viewModel.login()
            }
        }
    }

})