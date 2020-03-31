package com.lockwood.themoviedb.user.remote.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.user.data.model.AccountResponseEntity
import com.lockwood.themoviedb.user.remote.model.response.AccountResponseModel
import javax.inject.Inject

class AccountResponseMapper @Inject constructor() :
    EntityRemoteMapper<AccountResponseModel, AccountResponseEntity> {

    override fun mapFromRemote(type: AccountResponseModel): AccountResponseEntity {
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

    override fun mapToRemote(type: AccountResponseEntity): AccountResponseModel {
        val gravatar = AccountResponseModel.Avatar.Gravatar(type.avatar.gravatar.hash)
        val avatar = AccountResponseModel.Avatar(gravatar)
        return AccountResponseModel(
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