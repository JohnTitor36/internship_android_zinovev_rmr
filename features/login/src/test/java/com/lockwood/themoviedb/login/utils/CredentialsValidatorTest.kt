package com.lockwood.themoviedb.login.utils

import com.lockwood.themoviedb.login.domain.model.ValidateWithLoginBody
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

// Unit-тесты нетривиальной логики логина
object CredentialsValidatorTest : Spek({

    Feature("Check login and password contains forbidden characters") {

        //region Fields
        val simpleLogin = "ajj_the_band"
        val simplePassword = "123456"

        val loginBody by memoized {
            mock<ValidateWithLoginBody> {}
        }
        //endregion

        Scenario("login contains forbidden characters") {
            //region Fields
            var result = false
            //endregion

            Given("login with special characters") {
                mock<ValidateWithLoginBody> { on { loginBody.username } doReturn "ajj@%+/'!#^?:.(){}~" }
            }
            And("simple password") {
                mock<ValidateWithLoginBody> { on { loginBody.password } doReturn simplePassword }
            }

            When("validate login and password") {
                result = CredentialsValidator.isValidInput(loginBody.username, loginBody.password)
            }

            Then("it should be not valid") {
                assertFalse(result)
            }
        }

        Scenario("password contains forbidden characters") {
            //region Fields
            var result = false
            //endregion

            Given("simple login") {
                mock<ValidateWithLoginBody> { on { loginBody.username } doReturn simpleLogin }
            }
            And("password with forbidden special characters") {
                mock<ValidateWithLoginBody> { on { loginBody.password } doReturn "123456*#$%@#@$" }
            }

            When("validate login and password") {
                result = CredentialsValidator.isValidInput(loginBody.username, loginBody.password)
            }

            Then("it should be not valid") {
                assertFalse(result)
            }
        }

        Scenario("login contains approved characters") {
            //region Fields
            var result = false
            //endregion

            Given("simple login") {
                mock<ValidateWithLoginBody> { on { loginBody.username } doReturn simpleLogin }
            }
            And("simple password") {
                mock<ValidateWithLoginBody> { on { loginBody.password } doReturn simplePassword }
            }

            When("validate login and password") {
                result = CredentialsValidator.isValidInput(loginBody.username, loginBody.password)
            }

            Then("it should be valid") {
                assertTrue(result)
            }
        }

        Scenario("password contains approved special characters") {
            //region Fields
            var result = false
            //endregion

            Given("simple login") {
                mock<ValidateWithLoginBody> { on { loginBody.username } doReturn simpleLogin }
            }
            And("password with approved special characters") {
                mock<ValidateWithLoginBody> { on { loginBody.password } doReturn "123456_@%+/'!#^?:.(){}~" }
            }

            When("validate login and password") {
                result = CredentialsValidator.isValidInput(loginBody.username, loginBody.password)
            }

            Then("it should be valid") {
                assertTrue(result)
            }
        }
    }

    Feature("Check length of login and password is valid") {

        //region Fields
        val longEnoughLogin = "ajj_the_band"
        val longEnoughPassword = "123456"

        val loginBody by memoized {
            mock<ValidateWithLoginBody> {}
        }
        //endregion

        Scenario("too short credentials") {
            //region Fields
            var result = false
            //endregion

            //region Short login check
            Given("short login") {
                mock<ValidateWithLoginBody> { on { loginBody.username } doReturn "ajj" }
            }
            And("long enough password") {
                mock<ValidateWithLoginBody> { on { loginBody.password } doReturn longEnoughPassword }
            }

            When("validate login and password length") {
                result = CredentialsValidator.isValidLength(loginBody.username, loginBody.password)
            }

            Then("it should be not valid") {
                assertFalse(result)
            }
            //endregion

            //region Short password check
            Given("long enough login") {
                mock<ValidateWithLoginBody> { on { loginBody.username } doReturn longEnoughLogin }
            }
            And("short password") {
                mock<ValidateWithLoginBody> { on { loginBody.password } doReturn "123" }
            }

            When("validate login and password length") {
                result = CredentialsValidator.isValidLength(loginBody.username, loginBody.password)
            }

            Then("it should be not valid") {
                assertFalse(result)
            }
            //endregion
        }

        Scenario("long enough credentials") {
            //region Fields
            var result = false
            //endregion

            Given("long enough login") {
                mock<ValidateWithLoginBody> { on { loginBody.username } doReturn longEnoughLogin }
            }
            And("long enough password") {
                mock<ValidateWithLoginBody> { on { loginBody.password } doReturn longEnoughPassword }
            }

            When("validate login and password length") {
                result = CredentialsValidator.isValidLength(loginBody.username, loginBody.password)
            }

            Then("it should be valid") {
                assertTrue(result)
            }
        }

    }
})