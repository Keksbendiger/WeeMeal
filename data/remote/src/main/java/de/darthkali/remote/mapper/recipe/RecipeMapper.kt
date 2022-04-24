package de.darthkali.local.mapper.recipe

import de.darthkali.local.mapper.BaseMapper
import de.darthkali.model.recipe.Recipe
import de.darthkali.remote.mapper.model.recipe.RecipeJson

class RecipeMapper : BaseMapper<Recipe, RecipeJson> {
    override fun mapTo(dao: RecipeJson): Recipe {
        return Recipe(
            internalId = dao.internalId,
            name = dao.name,
        )
    }

    override fun mapBack(internal: Recipe): RecipeJson {
        return RecipeJson(
            internalId = internal.internalId,
            name = internal.name,
        )
    }
}
