package com.lockwood.themoviedb.login.data.source

import com.lockwood.themoviedb.login.presentation.model.Credentials
import io.reactivex.Completable

interface AuthRemoteDataSource {

    fun authUser(credentials: Credentials): Completable

}