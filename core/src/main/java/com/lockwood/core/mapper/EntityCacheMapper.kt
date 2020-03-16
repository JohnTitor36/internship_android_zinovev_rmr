package com.lockwood.core.mapper

interface EntityCacheMapper<M, E> {

    fun mapFromCached(type: M): E

    fun mapToCached(type: E): M

}