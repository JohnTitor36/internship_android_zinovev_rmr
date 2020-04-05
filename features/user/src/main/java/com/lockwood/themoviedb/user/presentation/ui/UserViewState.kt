package com.lockwood.themoviedb.user.presentation.ui

data class UserViewState(
    val username: String,
    val image: String
) {

    companion object {

        fun initialState(): UserViewState {
            return UserViewState(
                username = "",
                image = ""
            )
        }
    }

}