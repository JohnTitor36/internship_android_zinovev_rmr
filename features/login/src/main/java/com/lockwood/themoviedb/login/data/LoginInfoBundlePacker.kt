package com.lockwood.themoviedb.login.data

import android.os.Bundle

object LoginInfoBundlePacker {

    private object LoginInfoIdentifiers {
        const val LOGIN = "login_info_login"
        const val PASSWORD = "login_info_password"
    }

    fun loginInfoToBundle(loginInfo: LoginInfo) = Bundle().apply {
        putString(LoginInfoIdentifiers.LOGIN, loginInfo.login)
        putString(LoginInfoIdentifiers.PASSWORD, loginInfo.password)
    }

    fun loginInfoFromBundle(bundle: Bundle) = LoginInfo(
        login = bundle.getString(LoginInfoIdentifiers.LOGIN) ?: LoginInfo.DEFAULT.login,
        password = bundle.getString(LoginInfoIdentifiers.PASSWORD) ?: LoginInfo.DEFAULT.password
    )

}