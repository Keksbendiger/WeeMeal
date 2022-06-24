package de.fhe.ai.weemeal.local.mapper

import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.local.entity.IngredientEntity

fun IngredientEntity.toDomain() = Ingredient(
    internalId = this.id,
    name = this.name,
    image = image,
    quantity = quantity
)

fun Ingredient.fromDomain() = IngredientEntity(
    id = this.internalId,
    name = this.name,
    image = image,
    quantity = quantity
)
