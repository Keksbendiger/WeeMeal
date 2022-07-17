package de.fhe.ai.weemeal.usecases.recipe

import de.fhe.ai.weemeal.common.DataState
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchRecipes : KoinComponent {

    private val recipeRepository: RecipeRepository by inject()

    /**
     *
     * search all recipes by name
     * emits the result in a data object
     *
     * @return DataState
     */
    suspend fun execute(
        query: String,
    ): List<Recipe> {
        return recipeRepository.searchRecipeByName(
            recipeName = query,
        )
    }
}
