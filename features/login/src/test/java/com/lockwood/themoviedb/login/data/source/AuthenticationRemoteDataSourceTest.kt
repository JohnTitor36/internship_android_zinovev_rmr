package com.lockwood.themoviedb.login.data.source

import com.lockwood.test.extensions.emptyString
import com.lockwood.themoviedb.login.data.repository.AuthenticationRemote
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object AuthenticationRemoteDataSourceTest : Spek({

    Feature("Unsupported requests in remote throw Exception") {

        //region Fields
        val authenticationRemote by memoized {
            mock<AuthenticationRemote> { }
        }

        val remoteDataSource by memoized {
            AuthenticationRemoteDataSource(authenticationRemote)
        }
        //endregion

        Scenario("make unsupported fetch current request token") {
            //region Fields
            var isUnsupportedOperationException = false
            //endregion

            When("fetch current request token") {
                try {
                    remoteDataSource.fetchCurrentRequestToken()
                } catch (e: UnsupportedOperationException) {
                    isUnsupportedOperationException = true
                }
            }

            Then("UnsupportedOperationException was thrown") {
                assertTrue(isUnsupportedOperationException)
            }
        }

        Scenario("make unsupported fetch current session id") {
            //region Fields
            var isUnsupportedOperationException = false
            //endregion

            When("fetch current session id") {
                try {
                    remoteDataSource.fetchCurrentSessionId()
                } catch (e: UnsupportedOperationException) {
                    isUnsupportedOperationException = true
                }
            }

            Then("UnsupportedOperationException was thrown") {
                assertTrue(isUnsupportedOperationException)
            }
        }

        Scenario("make unsupported save current request token") {
            //region Fields
            val requestToken = emptyString()

            var isUnsupportedOperationException = false
            //endregion

            When("fetch current session id") {
                try {
                    remoteDataSource.saveCurrentRequestToken(requestToken)
                } catch (e: UnsupportedOperationException) {
                    isUnsupportedOperationException = true
                }
            }

            Then("UnsupportedOperationException was thrown") {
                assertTrue(isUnsupportedOperationException)
            }
        }

        Scenario("make unsupported save current session id") {
            //region Fields
            val sessionId = emptyString()

            var isUnsupportedOperationException = false
            //endregion

            When("fetch current session id") {
                try {
                    remoteDataSource.saveCurrentSessionId(sessionId)
                } catch (e: UnsupportedOperationException) {
                    isUnsupportedOperationException = true
                }
            }

            Then("UnsupportedOperationException was thrown") {
                assertTrue(isUnsupportedOperationException)
            }
        }
    }
})