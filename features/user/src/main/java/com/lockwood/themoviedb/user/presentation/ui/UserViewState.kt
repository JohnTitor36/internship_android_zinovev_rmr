package com.lockwood.themoviedb.user.presentation.ui

import com.lockwood.core.extensions.EMPTY

data class UserViewState(
    val username: String,
    val image: String
) {

    companion object {

        val initialState
            get() = UserViewState(
                username = String.EMPTY,
                image = String.EMPTY
            )

    }

}