package com.lockwood.themoviedb.login.presentation.ui

import android.content.Context
import android.net.ConnectivityManager
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.lockwood.core.event.Event
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.reader.ResourceReader
import com.lockwood.test.extensions.disableTestMode
import com.lockwood.test.extensions.enableTestMode
import com.lockwood.test.schedulers.TestSchedulersProvider
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.domain.model.CreateRequestTokenResponse
import com.lockwood.themoviedb.login.domain.model.CreateSessionResponse
import com.lockwood.themoviedb.login.domain.repository.AuthenticationRepository
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Completable
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object LoginViewModelIntegrationTest : Spek({

    //region Setup
    beforeGroup { enableTestMode() }
    afterGroup { disableTestMode() }
    //endregion

    Feature("Login") {

        //region Fields
        val apiKey = "79191836ddaa0da3df76a5ffef6f07ad6ab0c641"
        val expiresAt = "2016-08-26 17:04:39 UTC"
        val requsetToken = "ff5c7eeb5a8870efe3cd7fc5c282cffd26800ecd"
        val sessionId = "fgtra23215c7esdc5c282cffd26800ecd"
        val noInternetMessage = "Нет интернета"
        var isMainActivity = false

        val resourceReader by memoized { mock<ResourceReader> {} }
        val connectivityManager by memoized { mock<NetworkConnectivityManager> {} }
        val authenticationRepository by memoized { mock<AuthenticationRepository> {} }
        val userPreferences by memoized { mock<UserPreferences> {} }
        val schedulers by memoized { TestSchedulersProvider() }

        val viewModel by memoized {
            LoginViewModel(
                "",
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
        val toastTextView by memoized { mock<TextView> {} }
        val errorTextView by memoized { mock<TextView> {} }
        val loginEditText by memoized { mock<EditText> {} }
        val passwordEditText by memoized { mock<EditText> {} }
        val signInButton by memoized { mock<Button> {} }
        val progressBar by memoized { mock<ProgressBar> {} }
        val titleTextView by memoized { mock<ProgressBar> {} }
        val hintTextView by memoized { mock<ProgressBar> {} }
        //endregion


        //region Lifecycle
        fun showMessage(message: String) {
            mock<TextView> { on { toastTextView.text } doReturn message }
        }

        fun showError(error: String) {
            mock<TextView> { on { errorTextView.text } doReturn error }
            mock<TextView> { on { errorTextView.isVisible } doReturn error.isNotEmpty() }
        }

        fun observeNoInternetEvent() = with(viewModel) {
            val noInternetObserver = Observer<Event<Unit>> {
                val checkNetworkMessage =
                    resourceReader.string(R.string.title_check_network_connection)
                showMessage(checkNetworkMessage)
            }

            noInternetConnectionEvent.observeForever(noInternetObserver)
        }

        fun observeCredentialsInputChanges() = with(viewModel) {
            val validLengthObserver = Observer<String> { checkIsValidCredentialsLength() }

            loginLiveData.observeForever(validLengthObserver)
            passwordLiveData.observeForever(validLengthObserver)
        }

        fun observeCredentialsLengthChanges() = with(viewModel) {
            val loginButtonObserver = Observer<Boolean> { isEnabled ->
                mock<Button> { on { signInButton.isEnabled } doReturn isEnabled }
            }

            isCredentialsLengthValid.observeForever(loginButtonObserver)
        }

        fun observeRequestChanges() = with(viewModel) {
            val progressBarObserver = Observer<Boolean> { isVisible ->
                mock<ProgressBar> { on { progressBar.isVisible } doReturn isVisible }
            }

            val errorMessageObserver = Observer<String> { message ->
                if (message != null) {
                    showError(message)
                }
            }

            isLoadingLiveData.observeForever(progressBarObserver)
            errorMessageLiveData.observeForever(errorMessageObserver)
        }

        fun observeNavigationEvent() = with(viewModel) {
            val openNextActivityObserver = Observer<Event<Unit>> {
                isMainActivity = true
            }

            openNextActivityEvent.observeForever(openNextActivityObserver)
        }
        //endregion

        //region Response
        val requestTokenResponse by memoized {
            Single.just(CreateRequestTokenResponse(true, expiresAt, requsetToken))
        }

        val validateTokenResponse by memoized {
            Completable.complete()
        }

        val sessionResponse by memoized {
            Single.just(CreateSessionResponse(true, sessionId))
        }
        //endregion


        beforeEachScenario {
            observeCredentialsInputChanges()
            observeCredentialsLengthChanges()
            observeRequestChanges()
            observeNoInternetEvent()
            observeNavigationEvent()
        }

        Scenario("no internet login") {

            Given("resource reader that return no internet message") {
                mock<TextView> { on { resourceReader.string(R.string.title_check_network_connection) } doReturn noInternetMessage }
            }

            When("login") {
                viewModel.login()
            }

            Then("should be message with no internet") {
                assertThat(toastTextView.text).isEqualTo(noInternetMessage)
            }
        }

        // Тест фейлится, но оставил его чтобы посмотрели/подсказали как можно сделать
        Scenario("success login sequence") {

            Given("connectivity manager tell that there is internet connection") {
                mock<ConnectivityManager> { on { connectivityManager.hasInternetConnection } doReturn true }
            }
            And("createRequestToken return success response") {
                mock<AuthenticationRepository> {
                    on {
                        authenticationRepository.createRequestToken(
                            any()
                        )
                    } doReturn requestTokenResponse
                }
            }
            And("validateTokenWithLogin return success response") {
                mock<AuthenticationRepository> {
                    on {
                        authenticationRepository.validateTokenWithLogin(
                            any(), any()
                        )
                    } doReturn validateTokenResponse
                }
            }
            And("createSession return success response") {
                mock<AuthenticationRepository> {
                    on {
                        authenticationRepository.createSession(
                            any(), any()
                        )
                    } doReturn sessionResponse
                }
            }
            And("resource reader that return message") {
                mock<TextView> { on { resourceReader.string(R.string.title_eng_invalid_username_or_password) } doReturn "" }
                mock<TextView> { on { resourceReader.string(R.string.title_invalid_username_or_password) } doReturn "" }
            }

            When("login") {
                viewModel.login()
            }

            Then("should be succes loggin") {
                assertTrue(isMainActivity)
            }

        }

    }
})