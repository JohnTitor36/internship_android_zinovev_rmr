package com.lockwood.themoviedb.login.data.source

import com.lockwood.core.preferences.authentication.AuthenticationPreferences
import com.lockwood.test.emptyString
import com.lockwood.themoviedb.login.data.model.CreateSessionBodyEntity
import com.lockwood.themoviedb.login.data.model.ValidateWithLoginBodyEntity
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object AuthenticationCacheDataSourceTest : Spek({

    Feature("Unsupported requests in cache") {

        val authenticationPreferences by memoized {
            mock<AuthenticationPreferences> { }
        }

        val cacheDataSource by memoized {
            AuthenticationCacheDataSource(authenticationPreferences)
        }

        //region Functions
        fun emptyLoginBodyEntity(): ValidateWithLoginBodyEntity {
            return ValidateWithLoginBodyEntity(
                "", "", ""
            )
        }

        fun emptySessionBodyEntity(): CreateSessionBodyEntity {
            return CreateSessionBodyEntity(
                ""
            )
        }
        //endregion

        Scenario("make unsupported create request token request") {
            //region Fields
            val apiKey = emptyString()

            var isUnsupportedOperationException = false
            //endregion

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
            val apiKey = emptyString()
            val loginBody = emptyLoginBodyEntity()

            var isUnsupportedOperationException = false
            //endregion

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
            val apiKey = emptyString()
            val sessionBody = emptySessionBodyEntity()

            var isUnsupportedOperationException = false
            //endregion

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