package com.lockwood.core.ui

import com.spotify.mobius.MobiusLoop.Controller


abstract class BaseMobiusFragment<M, E, F> : BaseFragment() {

    protected lateinit var controller: Controller<M, E>

}