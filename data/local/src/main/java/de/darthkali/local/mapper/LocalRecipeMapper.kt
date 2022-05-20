package de.darthkali.local.mapper.recipe

import de.darthkali.domain.models.recipe.Recipe
import de.darthkali.local.recipe.RecipeEntity

fun RecipeEntity.toDomain(
) = Recipe(
    name = this.name,
    id = this.id
)

fun Recipe.fromDomain(

) = RecipeEntity(
    name = this.name,
    id = this.id
)
