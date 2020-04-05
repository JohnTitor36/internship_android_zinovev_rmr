package com.lockwood.themoviedb.user.data

import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.user.data.mapper.AccountResponseMapper
import com.lockwood.themoviedb.user.data.mapper.DeleteSessionBodyMapper
import com.lockwood.themoviedb.user.data.source.AccountRemoteDataSource
import com.lockwood.themoviedb.user.domain.model.AccountResponse
import com.lockwood.themoviedb.user.domain.model.DeleteSessionBody
import com.lockwood.themoviedb.user.domain.repository.AccountRepository
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

@FeatureScope
class DefaultAccountRepository @Inject constructor(
    private val accountRemoteDataSource: AccountRemoteDataSource,
    private val accountResponseMapper: AccountResponseMapper,
    private val deleteSessionBodyMapper: DeleteSessionBodyMapper
) : AccountRepository {

    override fun getAccountDetails( sessionId: String): Single<AccountResponse> {
        return accountRemoteDataSource.getAccountDetails( sessionId)
            .map { accountResponseMapper.mapFromEntity(it) }
    }

    override fun deleteSession( deleteSessionBody: DeleteSessionBody): Completable {
        val body = deleteSessionBodyMapper.mapToEntity(deleteSessionBody)
        return accountRemoteDataSource.deleteSession( body)
    }

}