package de.darthkali.local.recipe

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeEntityDao {
    @Query("SELECT * FROM RecipeEntity")
    fun getAllAsFlow(): Flow<List<RecipeEntity>>

    @Query("SELECT * FROM RecipeEntity")
    suspend fun getAll(): List<RecipeEntity>

    @Query("SELECT * FROM RecipeEntity WHERE id = :id")
    suspend fun get(id: Long): RecipeEntity?

    @Insert
    suspend fun insert(entity: RecipeEntity): Long

    @Delete
    suspend fun delete(entity: RecipeEntity)

    @Delete
    suspend fun delete(vararg entities: RecipeEntity)

    @Query("DELETE FROM RecipeEntity")
    suspend fun deleteAll()
}
