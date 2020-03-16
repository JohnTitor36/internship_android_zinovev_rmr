package com.lockwood.core.mapper

interface EntityRemoteMapper<M, E> {

    fun mapFromRemote(type: M): E

    fun mapToRemote(type: E): M

}