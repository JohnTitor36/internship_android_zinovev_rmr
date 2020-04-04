package com.lockwood.themoviedb.user.data.mapper

import com.lockwood.core.mapper.Mapper
import com.lockwood.themoviedb.user.data.model.AccountResponseEntity
import com.lockwood.themoviedb.user.domain.model.AccountResponse
import javax.inject.Inject

class AccountResponseMapper @Inject constructor() : Mapper<AccountResponseEntity, AccountResponse> {

    override fun mapFromEntity(type: AccountResponseEntity): AccountResponse {
        val gravatar = AccountResponse.Avatar.Gravatar(type.avatar.gravatar.hash)
        val avatar = AccountResponse.Avatar(gravatar)
        return AccountResponse(
            avatar,
            type.id,
            type.iso6391,
            type.iso31661,
            type.name,
            type.includeAdult,
            type.username
        )
    }

    override fun mapToEntity(type: AccountResponse): AccountResponseEntity {
        val gravatar = AccountResponseEntity.Avatar.Gravatar(type.avatar.gravatar.hash)
        val avatar = AccountResponseEntity.Avatar(gravatar)
        return AccountResponseEntity(
            avatar,
            type.id,
            type.iso6391,
            type.iso31661,
            type.name,
            type.includeAdult,
            type.username
        )
    }

}