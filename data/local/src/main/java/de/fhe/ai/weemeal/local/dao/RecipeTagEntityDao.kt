package de.fhe.ai.weemeal.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.fhe.ai.weemeal.local.entity.RecipeTagEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeTagEntityDao {
    @Query("SELECT * FROM RecipeTagEntity")
    fun getAllAsFlow(): Flow<List<RecipeTagEntity>>

    @Query("SELECT * FROM RecipeTagEntity")
    suspend fun getAll(): List<RecipeTagEntity>

    @Query("SELECT * FROM RecipeTagEntity WHERE id = :id")
    suspend fun get(id: Long): RecipeTagEntity?

    @Insert
    suspend fun insert(entity: RecipeTagEntity): Long

    @Delete
    suspend fun delete(entity: RecipeTagEntity)

    @Delete
    suspend fun delete(vararg entities: RecipeTagEntity)

    @Query("DELETE FROM RecipeTagEntity")
    suspend fun deleteAll()
}
