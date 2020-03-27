package com.lockwood.themoviedb.login.presentation.ui

import android.content.Context
import android.net.ConnectivityManager
import android.widget.TextView
import androidx.lifecycle.Observer
import com.lockwood.core.event.Event
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.reader.ResourceReader
import com.lockwood.test.extensions.disableTestMode
import com.lockwood.test.extensions.enableTestMode
import com.lockwood.test.schedulers.TestSchedulersProvider
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object LoginViewModelIntegrationTest : Spek({

    //region Setup
    beforeGroup { enableTestMode() }
    afterGroup { disableTestMode() }
    //endregion

    Feature("Login") {

        //region Fields
        val apiKey = "asdasdasd"
        val noInternetMessage = "Нет интернета"

        val resourceReader by memoized { mock<ResourceReader> {} }
        val connectivityManager by memoized { mock<NetworkConnectivityManager> {} }
        val authenticationRepository by memoized { mock<AuthenticationRepository> {} }
        val userPreferences by memoized { mock<UserPreferences> {} }
        val schedulers by memoized { TestSchedulersProvider() }

        val viewModel by memoized {
            LoginViewModel(
                apiKey,
                resourceReader,
                connectivityManager,
                authenticationRepository,
                userPreferences,
                schedulers
            )
        }
        //endregion

        //region View
        val context by memoized { mock<Context> {} }
        val errorTextView by memoized { mock<TextView> {} }
        //endregion

        //region Lifecycle
        fun showMessage(message: String) {
            mock<TextView> { on { errorTextView.text } doReturn message }
        }

        fun observeNoInternetEvent() = with(viewModel) {
            val noInternetObserver = Observer<Event<Unit>> {
                val checkNetworkMessage =
                    resourceReader.string(R.string.title_check_network_connection)
                showMessage(checkNetworkMessage)
            }
            noInternetConnectionEvent.observeForever(noInternetObserver)
        }
        //endregion

        beforeEachScenario {
            observeNoInternetEvent()
        }

        Scenario("no internet login") {

            Given("resource reader that return no internet message") {
                mock<TextView> { on { resourceReader.string(R.string.title_check_network_connection) } doReturn noInternetMessage }
            }
            And("connectivity manager tell that no internet connection") {
                mock<ConnectivityManager> { on { connectivityManager.hasInternetConnection } doReturn false }
            }

            When("login") {
                viewModel.login()
            }

            Then("should be message with no internet") {
                assertThat(errorTextView.text).isEqualTo(noInternetMessage)
            }
        }

    }
})