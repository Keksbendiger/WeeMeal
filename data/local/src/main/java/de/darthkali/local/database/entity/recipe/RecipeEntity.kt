package de.darthkali.local.database.entity.recipe

import androidx.room.Entity
import androidx.room.PrimaryKey

// In Entity umbenennen?
@Entity
data class RecipeEntity(
    @PrimaryKey (autoGenerate = true)
    var internalId: Long = 0,

    val name: String = "",
)
