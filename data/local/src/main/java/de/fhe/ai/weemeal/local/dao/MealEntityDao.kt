package de.fhe.ai.weemeal.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.fhe.ai.weemeal.local.entity.MealEntity
import kotlinx.coroutines.flow.Flow
import java.util.Date

@Dao
interface MealEntityDao {
    @Query("SELECT * FROM MealEntity")
    fun getAllAsFlow(): Flow<List<MealEntity>>

    @Query("SELECT * FROM MealEntity")
    suspend fun getAll(): List<MealEntity>

    @Query("SELECT * FROM MealEntity WHERE id = :id")
    suspend fun get(id: Long): MealEntity?

    @Query("SELECT * FROM MealEntity WHERE cookingDate >= :today")
    suspend fun getAllMealsWhereDateIsTodayOrLater(today: Date): List<MealEntity>

    @Insert
    suspend fun insert(entity: MealEntity): Long

    @Update
    suspend fun update(entity: MealEntity)

    @Delete
    suspend fun delete(entity: MealEntity)

    @Delete
    suspend fun delete(vararg entities: MealEntity)

    @Query("DELETE FROM MealEntity")
    suspend fun deleteAll()
}
