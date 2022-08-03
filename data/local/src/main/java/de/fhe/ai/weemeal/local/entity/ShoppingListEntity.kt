package de.fhe.ai.weemeal.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import de.fhe.ai.weemeal.domain.formats.QuantityFormat

@Entity
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var shoppingListId : Long = 0,
    var ingredientId: Long = 0,
)
