package de.darthkali.local.mapper.recipe

import de.darthkali.local.database.dao.recipe.RecipeDao
import de.darthkali.local.mapper.LocalBaseMapper
import de.darthkali.model.recipe.Recipe

class RecipeListMapperLocal : LocalBaseMapper<List<Recipe>, List<RecipeDao>> {

    private val internalMapper = RecipeMapperLocal()

    override fun mapTo(dao: List<RecipeDao>): List<Recipe> {
        val resultList = mutableListOf<Recipe>()

        dao.forEach {
            resultList.add(internalMapper.mapTo(it))
        }
        return resultList
    }

    override fun mapBack(internal: List<Recipe>): List<RecipeDao> {
        val resultList = mutableListOf<RecipeDao>()

        internal.forEach {
            resultList.add(internalMapper.mapBack(it))
        }
        return resultList
    }
}
