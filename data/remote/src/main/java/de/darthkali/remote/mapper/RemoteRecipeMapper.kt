package de.darthkali.local.mapper.recipe

import de.darthkali.domain.models.recipe.Recipe
import de.darthkali.remote.recipe.RecipeJson

fun RecipeJson.toRemote(
) = Recipe(
    name = this.name,
    id = this.id
)

fun Recipe.fromRemote(

) = RecipeJson(
    name = this.name,
    id = this.id
)
