package com.lockwood.themoviedb.user.data.mapper

import com.lockwood.core.mapper.Mapper
import com.lockwood.themoviedb.user.data.model.AccountResponseEntity
import com.lockwood.themoviedb.user.domain.model.AccountResponse
import javax.inject.Inject

class AccountResponseMapper @Inject constructor() : Mapper<AccountResponseEntity, AccountResponse> {

    override fun mapFromEntity(type: AccountResponseEntity): AccountResponse {
        with(type) {
            return AccountResponse(
                gravatarUrl,
                id,
                iso6391,
                iso31661,
                name,
                includeAdult,
                username
            )
        }
    }

    override fun mapToEntity(type: AccountResponse): AccountResponseEntity {
        with(type) {
            return AccountResponseEntity(
                gravatarUrl,
                id,
                iso6391,
                iso31661,
                name,
                includeAdult,
                username
            )
        }
    }

}