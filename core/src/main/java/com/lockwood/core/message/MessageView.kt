package com.lockwood.core.message

interface MessageView {

    fun showMessage(message: String)

    fun showError(error: String)

}