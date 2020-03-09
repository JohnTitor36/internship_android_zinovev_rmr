package com.lockwood.themoviedb.login

import android.os.Bundle
import com.lockwood.core.extensions.addFragmentIfNotExist
import com.lockwood.core.ui.BaseFragmentActivity

class LoginActivity : BaseFragmentActivity() {

    override val layoutId = R.layout.activity_login

    override val containerId = R.id.container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            addFragmentIfNotExist(containerId) { LoginFragment.newInstance() }
        }
    }

}