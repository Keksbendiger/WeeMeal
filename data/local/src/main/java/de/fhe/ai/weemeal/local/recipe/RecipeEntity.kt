package de.fhe.ai.weemeal.local.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val name: String,
    val defaultServings: Int? = 1,
//    val timePreparation: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"), // TODO: TimeFormat? in Common?
//    val timeActiveCooking: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"), // TODO: TimeFormat?
//    val timeOverall: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"), // TODO: TimeFormat?
    val instructions: String? = "",
    val image: String? = "",
//    val seasonality: List<String>? = listOf(), // TODO: Liste?
//    val mealTime: List<MealTime>? = listOf(), // TODO: Liste?
//    val optionalTags: List<Tag>? = listOf(), // TODO: Liste?
//    val defaultIngredients: List<Ingredient>? = listOf(),
//    -nutritionalValue : ?,
)
