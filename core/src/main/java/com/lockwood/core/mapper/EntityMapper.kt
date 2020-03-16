package com.lockwood.core.mapper

interface EntityMapper<M, E> {

    fun mapFromRemote(type: M): E

    fun mapToRemote(type: E): M

}