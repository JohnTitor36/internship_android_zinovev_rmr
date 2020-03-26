package com.lockwood.themoviedb.login.data.source

import com.lockwood.core.preferences.authentication.AuthenticationPreferences
import com.lockwood.themoviedb.login.data.model.CreateSessionBodyEntity
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.SoftAssertions.assertSoftly
import org.junit.jupiter.api.Assertions.assertTrue
import org.mockito.ArgumentMatchers.anyString
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object AuthenticationCacheDataSourceTest : Spek({

    Feature("Store authentication data in cache") {

        val authenticationPreferences by memoized {
            mock<AuthenticationPreferences> { }
        }

        val cacheDataSource by memoized {
            AuthenticationCacheDataSource(authenticationPreferences)
        }

        Scenario("fetch requestToken and sessionId data") {
            //region Fields
            val requestToken = anyString()
            val sessionId = anyString()

            var resultRequestToken = ""
            var resultSessionId = ""
            //endregion

            Given("requestToken and sessionId") {
                mock<ValidateWithLoginBody> { on { cacheDataSource.fetchCurrentRequestToken() } doReturn requestToken }
                mock<ValidateWithLoginBody> { on { cacheDataSource.fetchCurrentSessionId() } doReturn sessionId }
            }

            When("fetch requestToken and sessionId from data store") {
                resultRequestToken = cacheDataSource.fetchCurrentRequestToken()
                resultSessionId = cacheDataSource.fetchCurrentSessionId()
            }

            Then("fetched data should be the same") {
                assertSoftly {
                    assertThat(requestToken).isEqualTo(resultRequestToken)
                    assertThat(sessionId).isEqualTo(resultSessionId)
                }
            }
        }
    }

    Feature("Unsupported requests in cache") {

        val authenticationPreferences by memoized {
            mock<AuthenticationPreferences> { }
        }

        val cacheDataSource by memoized {
            AuthenticationCacheDataSource(authenticationPreferences)
        }

        //region Functions
        fun createApiKey(): String {
            return ""
        }

        fun createLoginBodyEntity(): ValidateWithLoginBodyEntity {
            return ValidateWithLoginBodyEntity(
                "", "", ""
            )
        }

        fun createSessionBodyEntity(): CreateSessionBodyEntity {
            return CreateSessionBodyEntity(
                ""
            )
        }
        //endregion

        Scenario("make unsupported create request token request") {
            //region Fields
            val apiKey = createApiKey()

            var isUnsupportedOperationException = false
            //endregion

            Given("UnsupportedOperationException wasn't thrown") {
                isUnsupportedOperationException = false
            }

            When("create request token") {
                try {
                    cacheDataSource.createRequestToken(apiKey)
                } catch (e: UnsupportedOperationException) {
                    isUnsupportedOperationException = true
                }
            }

            Then("UnsupportedOperationException was thrown") {
                assertTrue(isUnsupportedOperationException)
            }
        }

        Scenario("make unsupported validate token with login request") {
            //region Fields
            val apiKey = createApiKey()
            val loginBody = createLoginBodyEntity()

            var isUnsupportedOperationException = false
            //endregion

            Given("UnsupportedOperationException wasn't thrown") {
                isUnsupportedOperationException = false
            }

            When("validate token with login") {
                try {
                    cacheDataSource.validateTokenWithLogin(apiKey, loginBody)
                } catch (e: UnsupportedOperationException) {
                    isUnsupportedOperationException = true
                }
            }

            Then("UnsupportedOperationException was thrown") {
                assertTrue(isUnsupportedOperationException)
            }
        }

        Scenario("make unsupported create session request") {
            //region Fields
            val apiKey = createApiKey()
            val sessionBody = createSessionBodyEntity()

            var isUnsupportedOperationException = false
            //endregion

            Given("UnsupportedOperationException wasn't thrown") {
                isUnsupportedOperationException = false
            }

            When("create session") {
                try {
                    cacheDataSource.createSession(apiKey, sessionBody)
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