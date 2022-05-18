package de.darthkali.local.mapper.recipe

import de.darthkali.local.database.entity.recipe.RecipeEntity
import de.darthkali.local.mapper.LocalBaseMapper
import de.darthkali.domain.models.recipe.Recipe

class RecipeMapperLocal : LocalBaseMapper<Recipe, RecipeEntity> {
    override fun mapTo(entity: RecipeEntity): Recipe {
        return Recipe(
            internalId = entity.internalId,
            name = entity.name,
        )
    }

    override fun mapBack(internal: Recipe): RecipeEntity {
        return RecipeEntity(
            internalId = internal.internalId,
            name = internal.name,
        )
    }
}
