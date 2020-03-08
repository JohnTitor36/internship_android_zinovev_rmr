package com.lockwood.themoviedb.login

import com.lockwood.core.ui.BaseFragmentActivity

class LoginActivity() : BaseFragmentActivity() {

    override val layoutId = R.layout.activity_login

    override val containerId = R.id.container

    override fun showFragment() = checkFragmentOrInit { LoginFragment.newInstance() }

}