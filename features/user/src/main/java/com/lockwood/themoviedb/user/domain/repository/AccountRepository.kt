package com.lockwood.themoviedb.user.domain.repository

import com.lockwood.themoviedb.user.domain.model.AccountResponse
import com.lockwood.themoviedb.user.domain.model.DeleteSessionBody
import io.reactivex.Completable
import io.reactivex.Single

interface AccountRepository {

    fun getAccountDetails(
        sessionId: String
    ): Single<AccountResponse>

    fun deleteSession(
        deleteSessionBody: DeleteSessionBody
    ): Completable

}