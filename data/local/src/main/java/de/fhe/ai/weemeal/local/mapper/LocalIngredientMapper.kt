package de.fhe.ai.weemeal.local.mapper.recipe

import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.local.entity.IngredientEntity

// TODO: find a better solution for this
fun IngredientEntity.toDomain() = Ingredient(
    name = this.name,
)

fun Ingredient.fromDomain() = IngredientEntity(
    name = this.name
)
