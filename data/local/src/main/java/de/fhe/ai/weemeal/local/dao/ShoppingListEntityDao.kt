package de.fhe.ai.weemeal.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import de.fhe.ai.weemeal.local.entity.IngredientEntity
import de.fhe.ai.weemeal.local.entity.RecipeIngredientEntity
import de.fhe.ai.weemeal.local.entity.RecipeTagEntity
import de.fhe.ai.weemeal.local.entity.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListEntityDao {
    @Query("SELECT * FROM ShoppingListEntity WHERE id = :id")
    suspend fun get(id: Long): ShoppingListEntity?

    @Query("SELECT * FROM ShoppingListEntity")
    suspend fun getAll(): List<ShoppingListEntity>

    @Insert
    suspend fun insert(entity: ShoppingListEntity): Long

    @Delete
    suspend fun delete(entity: ShoppingListEntity)

    @Query("DELETE FROM ShoppingListEntity")
    suspend fun deleteAll()
}
