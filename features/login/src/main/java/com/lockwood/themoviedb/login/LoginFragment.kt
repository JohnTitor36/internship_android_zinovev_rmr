package com.lockwood.themoviedb.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lockwood.core.extensions.newFragment
import com.lockwood.core.ui.BaseFragment

class LoginFragment : BaseFragment() {

    companion object {

        fun newInstance() = newFragment<LoginFragment>()
    }

    override val hasOptionMenu = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_login, container, false)

}