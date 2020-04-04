package com.lockwood.themoviedb.user.data.repository

import com.lockwood.themoviedb.user.data.model.AccountResponseEntity
import com.lockwood.themoviedb.user.data.model.DeleteSessionBodyEntity
import io.reactivex.Completable
import io.reactivex.Single

interface AccountRemote {

    fun getAccountDetails(
        apiKey: String,
        sessionId: String
    ): Single<AccountResponseEntity>

    fun deleteSession(
        apiKey: String,
        deleteSessionBody: DeleteSessionBodyEntity
    ): Completable

}