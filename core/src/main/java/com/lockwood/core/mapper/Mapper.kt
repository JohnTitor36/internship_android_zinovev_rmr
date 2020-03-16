package com.lockwood.core.mapper

interface Mapper<T, R> {

    fun mapFromEntity(type: T): R

    fun mapToEntity(type: R): T

}