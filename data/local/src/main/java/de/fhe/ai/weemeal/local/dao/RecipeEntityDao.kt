package de.fhe.ai.weemeal.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.fhe.ai.weemeal.local.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeEntityDao {
    @Query("SELECT * FROM RecipeEntity")
    fun getAllAsFlow(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM RecipeEntity")
    suspend fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM RecipeEntity WHERE id = :id")
    suspend fun get(id: Long): RecipeEntity?

    @Query("SELECT * FROM RecipeEntity WHERE name LIKE '%' || :name || '%'")
    fun search(name: String?): List<RecipeEntity>

    @Insert
    suspend fun insert(entity: RecipeEntity): Long

    @Update
    suspend fun update(entity: RecipeEntity)

    @Delete
    suspend fun delete(entity: RecipeEntity)

    @Delete
    suspend fun delete(vararg entities: RecipeEntity)

    @Query("DELETE FROM RecipeEntity")
    suspend fun deleteAll()
}
