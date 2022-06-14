package de.fhe.ai.weemeal.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.fhe.ai.weemeal.domain.formats.TimeFormat

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    var name: String,
    var defaultServings: Int? = 1,
    var timePreparation: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    var timeActiveCooking: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    var timeOverall: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
    var instructions: String? = "",
    var image: Int = de.fhe.ai.weemeal.R.drawable.recipe_placeholder
)
