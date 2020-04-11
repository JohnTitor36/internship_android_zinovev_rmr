package com.lockwood.themoviedb.login.presentation.ui

import android.net.ConnectivityManager
import com.lockwood.core.network.manager.NetworkConnectivityManager
import com.lockwood.core.preferences.user.UserPreferences
import com.lockwood.core.reader.ResourceReader
import com.lockwood.test.extensions.*
import com.lockwood.test.schedulers.TestSchedulersProvider
import com.lockwood.themoviedb.login.R
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object LoginViewModelIntegrationTest : Spek({

    //region Fields

    // Мокаем Android related компоненты
    val mockResourceReader = mock<ResourceReader>()
    val mockConnectivityManager = mock<NetworkConnectivityManager>()
    val mockUserPreferences = mock<UserPreferences>()

    val testSchedulers by memoized { TestSchedulersProvider() }

    val viewModel by memoized {
        LoginViewModel(
            apiKey = "",
            authenticationRepository = mock { },
            resourceReader = mockResourceReader,
            connectivityManager = mockConnectivityManager,
            userPreferences = mockUserPreferences,
            schedulers = testSchedulers
        )
    }
    //endregion

    //region Setup
    beforeGroup { enableTestMode() }
    afterGroup { disableTestMode() }
    //endregion

    Feature("Login") {

        //region Setup sever
        val mockServer = MockWebServer()

        //region Setup server scenario
        fun setUpServerScenario() {
//            mockServer.start()
        }

        fun shutdownServerScenario() {
            mockServer.shutdown()
        }
        //endregion

        beforeEachScenario { setUpServerScenario() }
        afterEachScenario { shutdownServerScenario() }
        //endregion

        Scenario("click enter button And receive Invalid username and/or password error from server") {

            //region Fields
            val login = "johnny_appleseed"
            val password = "test123"

            val titleInvalidCredentialsRu = "Неверный логин или пароль"
            //endregion

            Given("invalid username and/or password error") {
                mockServer.dispatchResponses { path ->
                    return@dispatchResponses when (path) {
                        VALIDATE_TOKEN_WITH_LOGIN_PATH -> MockResponse().invalidCredentialsResponse()
                        else -> null
                    }
                }
            }
            And("read credentials error message from res") {
                mock<ConnectivityManager> { on { mockResourceReader.string(R.string.title_invalid_credentials) } doReturn titleInvalidCredentialsRu }
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