package de.fhe.ai.weemeal.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.fhe.ai.weemeal.domain.formats.QuantityFormat

@Entity
data class IngredientEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val name: String,
    val image: Int = de.fhe.ai.weemeal.R.drawable.recipe_placeholder,
    var quantity: QuantityFormat = QuantityFormat(quantity = 0.0f, unit = "ml"),
)
