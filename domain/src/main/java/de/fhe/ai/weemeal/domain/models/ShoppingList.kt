package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.BaseModel

data class ShoppingList(
    override var internalId: Long = 0,
    val items: List<Ingredient>
) : BaseModel
