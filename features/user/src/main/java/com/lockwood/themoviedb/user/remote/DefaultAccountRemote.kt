package com.lockwood.themoviedb.user.remote

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.user.data.model.AccountResponseEntity
import com.lockwood.themoviedb.user.data.model.DeleteSessionBodyEntity
import com.lockwood.themoviedb.user.data.repository.AccountRemote
import com.lockwood.themoviedb.user.remote.mapper.AccountResponseMapper
import com.lockwood.themoviedb.user.remote.mapper.DeleteSessionBodyMapper
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class DefaultAccountRemote @Inject constructor(
    private val accountService: AccountService,
    private val accountResponseMapper: AccountResponseMapper,
    private val deleteSessionBodyMapper: DeleteSessionBodyMapper
) : AccountRemote {

    override fun getAccountDetails(
        apiKey: String,
        sessionId: String
    ): Single<AccountResponseEntity> {
        return accountService.getAccountDetails(apiKey, sessionId)
            .map { accountResponseMapper.mapFromRemote(it) }
    }

    override fun deleteSession(
        apiKey: String,
        deleteSessionBody: DeleteSessionBodyEntity
    ): Completable {
        val body = deleteSessionBodyMapper.mapToRemote(deleteSessionBody)
        return accountService.deleteSession(apiKey, body)
    }

}