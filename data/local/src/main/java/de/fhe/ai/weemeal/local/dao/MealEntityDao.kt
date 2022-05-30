package de.fhe.ai.weemeal.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.fhe.ai.weemeal.local.entity.MealEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MealEntityDao {
    @Query("SELECT * FROM MealEntity")
    fun getAllAsFlow(): Flow<List<MealEntity>>

    @Query("SELECT * FROM MealEntity")
    suspend fun getAll(): List<MealEntity>

    @Query("SELECT * FROM MealEntity WHERE id = :id")
    suspend fun get(id: Long): MealEntity?

    @Insert
    suspend fun insert(entity: MealEntity): Long

    @Delete
    suspend fun delete(entity: MealEntity)

    @Delete
    suspend fun delete(vararg entities: MealEntity)

    @Query("DELETE FROM MealEntity")
    suspend fun deleteAll()
}
