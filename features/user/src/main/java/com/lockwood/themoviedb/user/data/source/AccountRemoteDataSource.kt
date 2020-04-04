package com.lockwood.themoviedb.user.data.source

import com.lockwood.themoviedb.user.data.model.AccountResponseEntity
import com.lockwood.themoviedb.user.data.model.DeleteSessionBodyEntity
import com.lockwood.themoviedb.user.data.repository.AccountDataStore
import com.lockwood.themoviedb.user.data.repository.AccountRemote
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class AccountRemoteDataSource @Inject constructor(
    private val accountRemote: AccountRemote
) : AccountDataStore {

    override fun getAccountDetails(
        apiKey: String,
        sessionId: String
    ): Single<AccountResponseEntity> {
        return accountRemote.getAccountDetails(apiKey, sessionId)
    }

    override fun deleteSession(
        apiKey: String,
        deleteSessionBody: DeleteSessionBodyEntity
    ): Completable {
        return accountRemote.deleteSession(apiKey, deleteSessionBody)
    }

}