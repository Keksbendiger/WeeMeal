package de.fhe.ai.weemeal.local.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeIngredientEntity(
    @PrimaryKey(autoGenerate = true)
    var ingredientId: Long = 0,
    val recipeId: Long,
)
