package de.fhe.ai.weemeal.domain

import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getUsers(): Flow<List<User>>
    suspend fun getUser( userId: Long ) : User?
    suspend fun insertUser( user: User ): Long
    suspend fun updateUser( user: User ): Long // Check Return Type

}

interface Logger {
    fun error( message: String )
    fun info( message: String )
}