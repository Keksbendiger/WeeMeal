package de.fhe.ai.weemeal.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.fhe.ai.weemeal.domain.enums.CookColor
import de.fhe.ai.weemeal.domain.formats.QuantityFormat
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.formats.TimeUnit
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.domain.models.Tag
import java.util.Date

@Entity
data class MealEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val recipeId: Long,
    val servings: Int? = 1,
    val cookColor: CookColor? = CookColor.TRANSPARENT,
    val cookingDate: Date, //TODO: TypeCOnverter???
    val shoppingListCreatedAt: Date? = null, //TODO: TypeCOnverter???
)
