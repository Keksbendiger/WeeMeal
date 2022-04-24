package de.darthkali.local.mapper

interface LocalBaseMapper<I, D> {
    fun mapTo(dao: D): I
    fun mapBack(internal: I): D
}
