package com.lockwood.themoviedb.login.domain

import android.os.Bundle
import com.lockwood.themoviedb.login.data.LoginInfo

object LoginInfoBundlePacker {

    private object LoginInfoIdentifiers {
        const val LOGIN = "login_info_login"
        const val PASSWORD = "login_info_password"
    }

    fun loginInfoToBundle(loginInfo: LoginInfo) = Bundle().also {
        it.putString(LoginInfoIdentifiers.LOGIN, loginInfo.login)
        it.putString(LoginInfoIdentifiers.PASSWORD, loginInfo.password)
    }

    fun loginInfoFromBundle(bundle: Bundle) =
        LoginInfo(
            login = bundle.getString(LoginInfoIdentifiers.LOGIN) ?: LoginInfo.DEFAULT.login,
            password = bundle.getString(LoginInfoIdentifiers.PASSWORD) ?: LoginInfo.DEFAULT.password
        )

}