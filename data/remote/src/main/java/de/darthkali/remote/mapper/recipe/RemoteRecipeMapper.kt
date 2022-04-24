package de.darthkali.local.mapper.recipe

import de.darthkali.remote.mapper.RemoteBaseMapper
import de.darthkali.model.recipe.Recipe
import de.darthkali.remote.model.recipe.RecipeJson

class RemoteRecipeMapper : RemoteBaseMapper<RecipeJson, Recipe> {
    override fun mapTo(data: RecipeJson): Recipe {
        return Recipe(
            internalId = data.internalId,
            name = data.name,
        )
    }

    override fun mapBack(internal: Recipe): RecipeJson {
        return RecipeJson(
            internalId = internal.internalId,
            name = internal.name,
        )
    }
}
