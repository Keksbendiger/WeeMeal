package de.darthkali.local.mapper.recipe

import de.darthkali.local.mapper.BaseMapper
import de.darthkali.model.recipe.Recipe
import de.darthkali.remote.mapper.model.recipe.RecipeJson

class RecipeListMapper : BaseMapper<List<Recipe>, List<RecipeJson>> {


    private val internalMapper = RecipeMapper()

    override fun mapTo(dao: List<RecipeJson>): List<Recipe> {
        val resultList = mutableListOf<Recipe>()

        dao.forEach {
            resultList.add(internalMapper.mapTo(it))
        }
        return resultList
    }

    override fun mapBack(internal: List<Recipe>): List<RecipeJson> {
        val resultList = mutableListOf<RecipeJson>()

        internal.forEach {
            resultList.add(internalMapper.mapBack(it))
        }
        return resultList
    }


}