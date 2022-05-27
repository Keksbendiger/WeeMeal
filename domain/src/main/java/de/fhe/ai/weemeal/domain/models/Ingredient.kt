package de.fhe.ai.weemeal.domain.models

import de.fhe.ai.weemeal.domain.formats.QuantityFormat

data class Ingredient(
    val name: String,
    val image: String? = "",
    var quantity: QuantityFormat = QuantityFormat(quantity = 0.0f, unit = "ml"), // TODO: QuantityFormat?
)
