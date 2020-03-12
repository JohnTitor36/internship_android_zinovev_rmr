package com.lockwood.core.toaster

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.widget.TextView
import android.widget.Toast
import com.lockwood.core.R
import com.lockwood.core.extensions.color
import com.lockwood.core.extensions.font

class DefaultToaster
constructor(
    private val ctx: Context
) : Toaster {

    override var currentToast: Toast? = null

    override fun toast(str: String, backgroundTint: Int, textColor: Int) {
        currentToast?.cancel()
        buildToast(str, backgroundTint, textColor).show()
    }

    override fun longToast(str: String, backgroundTint: Int, textColor: Int) {
        currentToast?.cancel()
        buildToast(str, backgroundTint, textColor, Toast.LENGTH_LONG).show()
    }

    @SuppressLint("ShowToast")
    override fun buildToast(
        str: String,
        backgroundTint: Int,
        textColor: Int,
        length: Int
    ): Toast {
        val sourceToast = Toast.makeText(ctx, "", length)
        val view = sourceToast.view
        view.backgroundTintList = ColorStateList.valueOf(ctx.color(backgroundTint))
        val toastTextView = view.findViewById(android.R.id.message) as TextView
        with(toastTextView){
            text = str
            setTextColor(ctx.color(textColor))
            typeface = ctx.font(R.font.roboto)
        }
        currentToast = sourceToast
        return sourceToast
    }

}
