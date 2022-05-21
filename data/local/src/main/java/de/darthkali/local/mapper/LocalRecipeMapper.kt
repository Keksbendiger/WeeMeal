package de.darthkali.local.mapper.recipe

import de.darthkali.domain.models.Recipe
import de.darthkali.local.recipe.RecipeEntity

fun RecipeEntity.toDomain() = Recipe(
    name = this.name,
)

fun Recipe.fromDomain() = RecipeEntity(
    name = this.name,
)
