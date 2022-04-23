package de.darthkali.local.mapper

interface BaseMapper<I, D> {
    fun mapTo(dao: D): I
    fun mapBack(internal: I): D
}