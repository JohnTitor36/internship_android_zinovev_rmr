package com.lockwood.core.snackbar

import android.content.Context
import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import com.lockwood.core.R
import com.lockwood.core.extensions.color
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SnackbarMaker @Inject
constructor(
    private val context: Context
) {

    fun snackbar(
        view: View,
        text: String,
        actionText: String? = null,
        action: (() -> Unit)? = null
    ) = buildSnackbar(view, text, actionText, action).show()


    fun longSnackbar(
        view: View,
        text: String,
        actionText: String? = null,
        action: (() -> Unit)? = null
    ) = buildSnackbar(view, text, actionText, action, BaseTransientBottomBar.LENGTH_LONG).show()


    private fun buildSnackbar(
        view: View,
        text: String,
        actionText: String? = null,
        action: (() -> Unit)? = null,
        length: Int = BaseTransientBottomBar.LENGTH_SHORT
    ) = Snackbar.make(view, text, length).apply {
        if (action != null) {
            setAction(actionText) { action.invoke() }
        }
        setTextColor(context.color(R.color.gray_light))
        getView().setBackgroundColor(context.color(R.color.dark_blue))
    }

}
