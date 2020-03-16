package com.lockwood.themoviedb.login.data.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.login.data.model.CreateSessionBodyEntity
import com.lockwood.themoviedb.login.domain.model.CreateSessionBody
import javax.inject.Inject

class CreateSessionBodyMapper @Inject constructor() :
    EntityRemoteMapper<CreateSessionBodyEntity, CreateSessionBody> {

    override fun mapFromRemote(type: CreateSessionBodyEntity): CreateSessionBody {
        return CreateSessionBody(type.requestToken)
    }

    override fun mapToRemote(type: CreateSessionBody): CreateSessionBodyEntity {
        return CreateSessionBodyEntity(type.requestToken)
    }

}