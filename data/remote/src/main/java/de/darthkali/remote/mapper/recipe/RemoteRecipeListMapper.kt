package de.darthkali.local.mapper.recipe

import de.darthkali.remote.mapper.RemoteBaseMapper
import de.darthkali.model.recipe.Recipe
import de.darthkali.remote.model.recipe.RecipeJson

class RemoteRecipeListMapper : RemoteBaseMapper<List<RecipeJson>, List<Recipe>> {

    private val internalMapper = RemoteRecipeMapper()

    override fun mapTo(data: List<RecipeJson>): List<Recipe> {
        val resultList = mutableListOf<Recipe>()

        data.forEach {
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
