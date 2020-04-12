package com.lockwood.themoviedb.login.presentation.ui

import com.lockwood.test.extensions.disableTestMode
import com.lockwood.test.extensions.enableTestMode
import com.lockwood.test.extensions.withKey
import com.lockwood.test.schedulers.TestSchedulersProvider
import com.lockwood.themoviedb.login.AuthenticationNetworkEnvironment
import com.lockwood.themoviedb.login.AuthenticationNetworkEnvironment.Companion.DEFAULT_API_KEY
import com.lockwood.themoviedb.login.AuthenticationNetworkEnvironment.Companion.DEFAULT_REQUEST_TOKEN
import com.lockwood.themoviedb.login.R
import com.lockwood.themoviedb.login.extensions.VALIDATE_TOKEN_WITH_LOGIN_PATH
import com.lockwood.themoviedb.login.extensions.createLoginViewModel
import com.lockwood.themoviedb.login.extensions.invalidCredentialsResponse
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.willReturn
import okhttp3.mockwebserver.MockResponse
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object LoginViewModelIntegrationTest : Spek({

    //region Fields
    val networkEnvironment = AuthenticationNetworkEnvironment()

    var viewModel = createLoginViewModel(DEFAULT_API_KEY, mock())
    //endregion

    //region Setup
    beforeGroup { enableTestMode() }
    afterGroup { disableTestMode() }
    //endregion

    Feature("Login") {

        //region Setup sever
        fun setUpServerScenario() {
            with(networkEnvironment) {
                setupServer()
                viewModel = createLoginViewModel(
                    apiKey = DEFAULT_API_KEY,
                    authenticationRepository = authenticationRepository,
                    resourceReader = mockResourceReader,
                    connectivityManager = mockConnectivityManager,
                    schedulers = TestSchedulersProvider()
                )
            }
        }

        fun shutdownServerScenario() {
            networkEnvironment.shutdownServer()
        }
        //endregion

        beforeEachScenario { setUpServerScenario() }
        afterEachScenario { shutdownServerScenario() }

        Scenario("click enter button And receive Invalid username and/or password error from server") {

            //region Fields
            val login = "johnny_appleseed"
            val password = "test123"

            val titleInvalidCredentialsRu = "Неверный логин или пароль"
            val titleInvalidCredentialsRegex = "Invalid (username|password)"
            //endregion

            Given("invalid username and/or password error") {
                networkEnvironment.dispatchResponses { path ->
                    return@dispatchResponses when (path) {
                        VALIDATE_TOKEN_WITH_LOGIN_PATH.withKey(DEFAULT_API_KEY) -> MockResponse().invalidCredentialsResponse()
                        else -> null // default
                    }
                }
            }
            And("has internet connection") {
                with(networkEnvironment) {
                    given { mockConnectivityManager.hasInternetConnection } willReturn { true }
                }
            }
            And("fetch valid request token") {
                with(networkEnvironment) {
                    given { authenticationRepository.fetchCurrentRequestToken() } willReturn { DEFAULT_REQUEST_TOKEN }
                }
            }
            And("read credentials error message from res") {
                with(networkEnvironment) {
                    given { mockResourceReader.string(R.string.title_invalid_credentials) } willReturn { titleInvalidCredentialsRu }
                    given { mockResourceReader.string(R.string.title_eng_invalid_credentials) } willReturn { titleInvalidCredentialsRegex }
                }
            }

            When("enter credentials with not correct password") {
                viewModel.onCredentialsChanged(login, password)
            }
            And("click enter button") {
                viewModel.onEnterButtonClick()
            }

            Then("show screen without loading and with error message about invalid credentials") {
                val state = viewModel.liveState.value
                val expectedState = LoginViewState(
                    login = login,
                    password = password,
                    errorMessage = titleInvalidCredentialsRu,
                    validCredentials = true,
                    loading = false,
                    keyboardOpened = false
                )
                assertThat(state).isEqualTo(expectedState)
            }
        }
    }

})