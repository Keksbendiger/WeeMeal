package de.fhe.ai.weemeal.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.fhe.ai.weemeal.domain.formats.QuantityFormat
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.formats.TimeUnit
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.Tag

@Entity
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val name: String,
    val image: String? = "",
    var quantity: QuantityFormat = QuantityFormat(quantity = 0.0f, unit = "ml"), // TODO: TypeConverter?
)
