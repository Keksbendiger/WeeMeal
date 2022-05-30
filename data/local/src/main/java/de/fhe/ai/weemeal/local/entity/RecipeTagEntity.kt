package de.fhe.ai.weemeal.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeTagEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var recipeId: Long = 0,
    val tagId: Long,
)
