package de.darthkali.remote.mapper

interface RemoteBaseMapper<F, T> {
    fun mapTo(data: F): T
    fun mapBack(internal: T): F
}
