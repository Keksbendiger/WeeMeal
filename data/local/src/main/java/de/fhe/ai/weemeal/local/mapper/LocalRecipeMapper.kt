package de.fhe.ai.weemeal.local.mapper.recipe

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.local.recipe.RecipeEntity

fun RecipeEntity.toDomain() = Recipe(
    name = this.name,
)

fun Recipe.fromDomain() = RecipeEntity(
    name = this.name,
)
