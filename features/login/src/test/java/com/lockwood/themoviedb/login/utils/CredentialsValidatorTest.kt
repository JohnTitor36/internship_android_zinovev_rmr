package com.lockwood.themoviedb.login.utils

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class CredentialsValidatorTest {

    @ParameterizedTest
    @ValueSource(strings = ["redmadrobot", "ajj_the_band", "123456"])
    fun `when check credentials by some login and valid password - should return true`(login: String) {
        // given
        val validPassword = "123456"
        // when
        val result = CredentialsValidator.isValidInput(login, validPassword)
        // then
        assertThat(result).isEqualTo(true)
    }

    @ParameterizedTest
    @ValueSource(strings = ["123456", "*#$%@#@$", "qwerty"])
    fun `when check credentials by valid login and some password - should return true`(password: String) {
        // given
        val validLogin = "123456"
        // when
        val result = CredentialsValidator.isValidInput(validLogin, validLogin)
        // then
        assertThat(result).isEqualTo(true)
    }

    @ParameterizedTest
    @ValueSource(strings = ["redmadrobot@%+/'!.", "ajj_the_band#^?:", "123456(){}~"])
    fun `when check credentials by login with forbidden characters and valid password - should return false`(login: String) {
        // given
        val validPassword = "123456"
        // when
        val result = CredentialsValidator.isValidInput(login, validPassword)
        // then
        assertThat(result).isEqualTo(false)
    }

    @ParameterizedTest
    @ValueSource(strings = ["123", "пароль кириллицей", "password with spaces"])
    fun `when check credentials by valid login and password with forbidden characters - should return false`(password: String) {
        // given
        val validLogin = "123456"
        // when
        val result = CredentialsValidator.isValidInput(validLogin, password)
        // then
        assertThat(result).isEqualTo(false)
    }

}