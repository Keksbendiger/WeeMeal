package de.fhe.ai.weemeal.local.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.fhe.ai.weemeal.domain.enums.MealTime
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.Tag

@Entity
data class RecipeIngredientEntity(
    @PrimaryKey(autoGenerate = true)
    var ingredientId: Long = 0,
    val recipeId: Long,
)