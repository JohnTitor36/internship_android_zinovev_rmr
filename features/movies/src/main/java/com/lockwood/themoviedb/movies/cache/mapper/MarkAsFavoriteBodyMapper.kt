package com.lockwood.themoviedb.movies.cache.mapper

import com.lockwood.core.mapper.EntityCacheMapper
import com.lockwood.themoviedb.movies.cache.model.MarkAsFavoriteBodyDb
import com.lockwood.themoviedb.movies.data.model.MarkAsFavoriteBodyEntity
import javax.inject.Inject

class MarkAsFavoriteBodyMapper @Inject constructor() :
    EntityCacheMapper<MarkAsFavoriteBodyDb, MarkAsFavoriteBodyEntity> {

    override fun mapToCached(type: MarkAsFavoriteBodyEntity): MarkAsFavoriteBodyDb {
        with(type) {
            return MarkAsFavoriteBodyDb(
                mediaType = mediaType,
                mediaId = mediaId,
                favorite = favorite
            )
        }
    }

    override fun mapFromCached(type: MarkAsFavoriteBodyDb): MarkAsFavoriteBodyEntity {
        with(type) {
            return MarkAsFavoriteBodyEntity(
                mediaType = mediaType,
                mediaId = mediaId,
                favorite = favorite
            )
        }
    }

}