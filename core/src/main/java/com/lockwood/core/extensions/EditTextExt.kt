package com.lockwood.core.extensions

import android.text.Editable
import android.text.TextWatcher

fun simpleTextWatcher(
    textChanged: (String) -> Unit
) = object :TextWatcher {

    override fun afterTextChanged(s: Editable?) = Unit

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int)  = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
       textChanged(s.toString())
    }

}