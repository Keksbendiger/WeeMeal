package de.fhe.ai.weemeal.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeIngredientEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var ingredientId: Long = 0,
    val recipeId: Long,
)
