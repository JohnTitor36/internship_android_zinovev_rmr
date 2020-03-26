package com.lockwood.themoviedb.login.utils

import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody
import com.lockwood.themoviedb.login.generator.ValidateWithLoginBodyGenerator
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

// Понимаю что в принципе тестировать такое не нужно,
// но сначала хотел попробовать spek на более простом
object CredentialsValidatorTest : Spek({
    Feature("Validate length of login and password") {

        val loginBody by memoized {
            mock<ValidateWithLoginBody> {
                ValidateWithLoginBodyGenerator.createValidateWithLoginBody()
            }
        }

        Scenario("too short credentials") {
            Given("input login and password") {
                mock<ValidateWithLoginBody> { on { loginBody.username } doReturn "ajj" }
                mock<ValidateWithLoginBody> { on { loginBody.password } doReturn "123456" }
            }

            var result = false

            When("validate login and password") {
                result = CredentialsValidator.isValidLength(loginBody.username, loginBody.password)
            }

            Then("it should be not valid") {
                assertFalse(result)
            }
        }

        Scenario("long enough credentials") {
            Given("input login and password") {
                mock<ValidateWithLoginBody> { on { loginBody.username } doReturn "ajj_the_band" }
                mock<ValidateWithLoginBody> { on { loginBody.password } doReturn "123456" }
            }

            var result = false

            When("validate login and password") {
                result = CredentialsValidator.isValidLength(loginBody.username, loginBody.password)
            }

            Then("it should be valid") {
                assertTrue(result)
            }
        }

    }
})