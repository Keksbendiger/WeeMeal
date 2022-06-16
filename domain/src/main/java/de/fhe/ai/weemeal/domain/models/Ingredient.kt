package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.BaseModel
import de.fhe.ai.weemeal.domain.formats.QuantityFormat

data class Ingredient(
    override var internalId: Long = 0,
    var name: String,
    val image: String? = "",
    var quantity: QuantityFormat = QuantityFormat(quantity = 0.0f, unit = "ml"),
): BaseModel
