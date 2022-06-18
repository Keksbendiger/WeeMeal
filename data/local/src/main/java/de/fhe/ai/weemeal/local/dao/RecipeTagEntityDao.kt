package de.fhe.ai.weemeal.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.fhe.ai.weemeal.local.entity.RecipeEntity
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

    @Query("SELECT * FROM RecipeTagEntity WHERE recipeId = :recipeId")
    suspend fun getAllByRecipeId(recipeId: Long): List<RecipeTagEntity>

    @Insert
    suspend fun insert(entity: RecipeTagEntity): Long

    @Update
    suspend fun update(entity: RecipeTagEntity)

    @Delete
    suspend fun delete(entity: RecipeTagEntity)

    @Delete
    suspend fun delete(vararg entities: RecipeTagEntity)

    @Query("DELETE FROM RecipeTagEntity")
    suspend fun deleteAll()
}
