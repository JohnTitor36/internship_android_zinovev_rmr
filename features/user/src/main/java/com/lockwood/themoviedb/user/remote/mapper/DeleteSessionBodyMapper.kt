package com.lockwood.themoviedb.user.remote.mapper

import com.lockwood.core.mapper.EntityRemoteMapper
import com.lockwood.themoviedb.user.data.model.DeleteSessionBodyEntity
import com.lockwood.themoviedb.user.remote.model.body.DeleteSessionBodyModel
import javax.inject.Inject

class DeleteSessionBodyMapper @Inject constructor() :
    EntityRemoteMapper<DeleteSessionBodyModel, DeleteSessionBodyEntity> {

    override fun mapFromRemote(type: DeleteSessionBodyModel): DeleteSessionBodyEntity {
        return DeleteSessionBodyEntity(sessionId = type.sessionId)
    }

    override fun mapToRemote(type: DeleteSessionBodyEntity): DeleteSessionBodyModel {
        return DeleteSessionBodyModel(sessionId = type.sessionId)
    }

}