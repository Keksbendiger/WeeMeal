package de.fhe.ai.weemeal.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val name: String,
    val defaultServings: Int? = 1,
//    val timePreparation: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"), // TODO: TypeConverter
//    val timeActiveCooking: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"), // TODO: TypeConverter
//    val timeOverall: TimeFormat? = TimeFormat(value = 0.0f, unit = "h"), // TODO: TypeConverter
    val instructions: String? = "",
    val image: String? = "",
//    val tags: List<Tag>? = Tag.generateDefaultTagList(), // TODO: TypeConverter

)
