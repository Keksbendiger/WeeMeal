package de.fhe.ai.weemeal.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import de.fhe.ai.weemeal.local.entity.RecipeIngredientEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeIngredientEntityDao {
    @Query("SELECT * FROM RecipeIngredientEntity")
    fun getAllAsFlow(): Flow<List<RecipeIngredientEntity>>

    @Query("SELECT * FROM RecipeIngredientEntity")
    suspend fun getAll(): List<RecipeIngredientEntity>

    @Query("SELECT * FROM RecipeIngredientEntity WHERE id = :id")
    suspend fun get(id: Long): RecipeIngredientEntity?

    @Query("SELECT * FROM RecipeIngredientEntity WHERE recipeId = :recipeId")
    suspend fun getAllByRecipeId(recipeId: Long): List<RecipeIngredientEntity>

    @Insert
    suspend fun insert(entity: RecipeIngredientEntity): Long

    @Delete
    suspend fun delete(entity: RecipeIngredientEntity)

    @Delete
    suspend fun delete(vararg entities: RecipeIngredientEntity)

    @Query("DELETE FROM RecipeIngredientEntity")
    suspend fun deleteAll()
}
