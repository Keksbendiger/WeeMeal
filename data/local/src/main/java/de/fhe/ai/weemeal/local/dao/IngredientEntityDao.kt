package de.fhe.ai.weemeal.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.fhe.ai.weemeal.local.entity.IngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface IngredientEntityDao {
    @Query("SELECT * FROM IngredientEntity")
    fun getAllAsFlow(): Flow<List<IngredientEntity>>

    @Query("SELECT * FROM IngredientEntity")
    suspend fun getAll(): List<IngredientEntity>

    @Query("SELECT * FROM IngredientEntity WHERE id = :id")
    suspend fun get(id: Long): IngredientEntity?

    @Query("SELECT * FROM IngredientEntity WHERE name LIKE '%' || :name || '%'")
    fun search(name: String?): Flow<List<IngredientEntity>>

    @Insert
    suspend fun insert(entity: IngredientEntity): Long

    @Update
    suspend fun update(entity: IngredientEntity)

    @Delete
    suspend fun delete(entity: IngredientEntity)

    @Delete
    suspend fun delete(vararg entities: IngredientEntity)

    @Query("DELETE FROM IngredientEntity")
    suspend fun deleteAll()
}
