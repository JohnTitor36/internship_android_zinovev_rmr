package com.lockwood.themoviedb.login.data

data class LoginInfo(val login: String, val password: String) {

    companion object {

        val DEFAULT = LoginInfo("", "")
    }

}