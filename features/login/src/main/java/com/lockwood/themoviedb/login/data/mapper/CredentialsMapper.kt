package com.lockwood.themoviedb.login.data.mapper

import com.lockwood.core.data.Mapper
import com.lockwood.core.di.scope.FeatureScope
import com.lockwood.themoviedb.login.data.model.CredentialsEntity
import com.lockwood.themoviedb.login.presentation.model.Credentials
import javax.inject.Inject

@FeatureScope
class CredentialsMapper @Inject constructor() : Mapper<CredentialsEntity, Credentials> {

    override fun mapFromEntity(type: CredentialsEntity): Credentials {
        return Credentials(type.login, type.password)
    }

    override fun mapToEntity(type: Credentials): CredentialsEntity {
        return CredentialsEntity(type.login, type.password)
    }

}