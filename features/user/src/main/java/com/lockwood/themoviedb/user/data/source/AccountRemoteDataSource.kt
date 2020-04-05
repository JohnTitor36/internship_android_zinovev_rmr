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
        sessionId: String
    ): Single<AccountResponseEntity> {
        return accountRemote.getAccountDetails(sessionId)
    }

    override fun deleteSession(
        deleteSessionBody: DeleteSessionBodyEntity
    ): Completable {
        return accountRemote.deleteSession(deleteSessionBody)
    }

}