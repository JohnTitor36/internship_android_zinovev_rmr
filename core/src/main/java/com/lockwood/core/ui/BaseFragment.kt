package com.lockwood.core.ui

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lockwood.core.message.MessageView

abstract class BaseFragment(@LayoutRes layoutId: Int) : Fragment(layoutId), MessageView {

    protected lateinit var rootView: View

    abstract val hasOptionMenu: Boolean

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(hasOptionMenu)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rootView = view
    }

    protected fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(rootView.windowToken, 0)
        rootView.clearFocus()
    }

    override fun showMessage(message: String) = Unit

    override fun showError(error: String) = Unit

}