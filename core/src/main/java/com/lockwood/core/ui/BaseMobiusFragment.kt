package com.lockwood.core.ui

import android.os.Bundle
import com.spotify.mobius.MobiusLoop.Controller

abstract class BaseMobiusFragment<M, E, F> : BaseFragment() {

    protected lateinit var controller: Controller<M, E>

    abstract fun resolveDefaultModel(savedInstanceState: Bundle?): M

    override fun onResume() {
        super.onResume()
        controller.start()
    }

    override fun onPause() {
        controller.stop()
        super.onPause()
    }

    override fun onDestroyView() {
        controller.disconnect()
        super.onDestroyView()
    }

}