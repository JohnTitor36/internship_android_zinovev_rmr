package com.lockwood.themoviedb.login.remote.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.login.data.model.CreateSessionBodyEntity
import com.lockwood.themoviedb.login.remote.model.body.CreateSessionBodyModel
import javax.inject.Inject

class CreateSessionBodyEntityMapper @Inject constructor() :
    EntityRemoteMapper<CreateSessionBodyModel, CreateSessionBodyEntity> {

    override fun mapFromRemote(type: CreateSessionBodyModel): CreateSessionBodyEntity {
        return CreateSessionBodyEntity(type.requestToken)
    }

    override fun mapToRemote(type: CreateSessionBodyEntity): CreateSessionBodyModel {
        return CreateSessionBodyModel(type.requestToken)
    }

}