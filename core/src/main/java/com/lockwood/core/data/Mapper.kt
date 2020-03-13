package com.lockwood.core.data

interface Mapper<T, R> {

    fun mapFromEntity(type: T): R

    fun mapToEntity(type: R): T

}