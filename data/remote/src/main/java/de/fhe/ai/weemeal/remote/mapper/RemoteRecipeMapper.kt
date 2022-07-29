package de.fhe.ai.weemeal.local.mapper.recipe

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.remote.recipe.RecipeJson

/**
 * remote is currently not in use, but will implemented in a further release
 */
fun RecipeJson.toRemote() = Recipe(
    name = this.name,
)

fun Recipe.fromRemote() = RecipeJson(
    name = this.name,
)
