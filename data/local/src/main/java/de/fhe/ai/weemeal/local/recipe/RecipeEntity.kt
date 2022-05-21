package de.fhe.ai.weemeal.local.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    val name: String
)
