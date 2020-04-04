package com.lockwood.themoviedb.login.data.source

import com.lockwood.test.extensions.catchException
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThat
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object AuthenticationCacheDataSourceTest : Spek({

    Feature("Unsupported requests in cache throw Exception") {

        //region Fields
        val cacheDataSource by memoized {
            AuthenticationCacheDataSource(mock())
        }
        //endregion

        Scenario("make unsupported create request token request") {
            //region Fields
            var exception: Exception? = null
            //endregion

            When("create request token") {
                exception = catchException { cacheDataSource.createRequestToken("") }
            }

            Then("UnsupportedOperationException was thrown") {
                assertThat(exception).isInstanceOf(UnsupportedOperationException::class.java)
            }
        }
    }

})