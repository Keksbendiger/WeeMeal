package de.fhe.ai.weemeal.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserEntityDao {

    @Query("SELECT * FROM UserEntity")
    fun getAllAsFlow(): Flow<List<UserEntity>>

    @Query("SELECT * FROM UserEntity")
    suspend fun getAll(): List<UserEntity>

    @Query("SELECT * FROM UserEntity WHERE id = :id")
    suspend fun get(id: Long): UserEntity?

    @Insert
    suspend fun insert(entity: UserEntity): Long

    @Delete
    suspend fun delete(entity: UserEntity)

    @Delete
    suspend fun delete(vararg entities: UserEntity)

    @Query("DELETE FROM UserEntity")
    suspend fun deleteAll()

}
