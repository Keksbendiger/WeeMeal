package de.darthkali.domain.models

import de.fhe.ai.weemeal.common.formats.QuantityFormat

data class Ingredient(
    val name: String,
    val image: String? = "",
    var quantity: QuantityFormat = QuantityFormat(quantity = 0.0f, unit = "ml"), // TODO: QuantityFormat?
    //    val nutritionalValue? : ?
)
