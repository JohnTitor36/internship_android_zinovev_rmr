package com.lockwood.themoviedb.user.presentation.ui

data class UserViewState(
    val username: String,
    val image: String
) {

    companion object {

        val initialState
            get() = UserViewState(
                username = "",
                image = ""
            )

    }

}