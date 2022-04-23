package de.darthkali.local.mapper.recipe

import de.darthkali.local.database.dao.recipe.RecipeDao
import de.darthkali.local.mapper.BaseMapper
import de.darthkali.model.recipe.Recipe

class RecipeMapper : BaseMapper<Recipe, RecipeDao> {
    override fun mapTo(dao: RecipeDao): Recipe {
        return Recipe(
            internalId = dao.internalId,
            name = dao.name,
        )
    }


    override fun mapBack(internal: Recipe): RecipeDao {
        return RecipeDao(
            internalId = internal.internalId,
            name = internal.name,
        )
    }
}