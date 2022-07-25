package de.fhe.ai.weemeal.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.fhe.ai.weemeal.domain.enums.CookColor
import java.util.Date

@Entity
data class MealEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val recipeId: Long,
    val servings: Int? = 1,
    val cookColor: CookColor = CookColor.TRANSPARENT,
    val cookingDate: Date,
    val shoppingListCreatedAt: Date? = null,
)
