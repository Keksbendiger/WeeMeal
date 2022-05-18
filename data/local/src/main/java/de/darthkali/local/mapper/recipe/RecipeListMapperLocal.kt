package de.darthkali.local.mapper.recipe

import de.darthkali.local.database.entity.recipe.RecipeEntity
import de.darthkali.local.mapper.LocalBaseMapper
import de.darthkali.domain.models.recipe.Recipe

class RecipeListMapperLocal : LocalBaseMapper<List<Recipe>, List<RecipeEntity>> {

    private val internalMapper = RecipeMapperLocal()

    override fun mapTo(entity: List<RecipeEntity>): List<Recipe> {
        val resultList = mutableListOf<Recipe>()

        entity.forEach {
            resultList.add(internalMapper.mapTo(it))
        }
        return resultList
    }

    override fun mapBack(internal: List<Recipe>): List<RecipeEntity> {
        val resultList = mutableListOf<RecipeEntity>()

        internal.forEach {
            resultList.add(internalMapper.mapBack(it))
        }
        return resultList
    }
}
