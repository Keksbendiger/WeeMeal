package de.darthkali.local.mapper.recipe

import de.darthkali.domain.models.Recipe
import de.darthkali.remote.recipe.RecipeJson

fun RecipeJson.toRemote() = Recipe(
    name = this.name,
)

fun Recipe.fromRemote() = RecipeJson(
    name = this.name,
)
