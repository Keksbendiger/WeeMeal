package de.darthkali.domain.models

import de.darthkali.domain.models.enums.Mealtime
import de.fhe.ai.weemeal.common.formats.TimeFormat


data class Recipe(
    val name: String,
    val defaultServings: Int? = 1,
    val defaultIngredients: List<Ingredient>? = listOf(),
    val timePreparation: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),        // TODO: TimeFormat?
    val timeActiveCooking: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),      // TODO: TimeFormat?
    val timeOverall: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),            // TODO: TimeFormat?
    val instructions: String? = "",
    val image: String? = "",
    val seasonality: List<String>? = listOf(),                                      // TODO: Liste?
    val mealtime: List<Mealtime>? = listOf(),                                       // TODO: Liste?
    val optionalTags: List<Tag>? = listOf(),                                        // TODO: Liste?
//    -nutritionalValue : ?,
)
