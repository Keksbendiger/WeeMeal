package de.fhe.ai.weemeal.usecases

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
     * search all meals with the cooking date from today or ahead
     * emits the result in a data object
     *
     * @return DataState
     */
    fun execute(
        query: String,
    ): Flow<DataState<List<Recipe>>> = flow {

        emit(DataState.loading())
        val recipeList =
            recipeRepository.searchRecipeByName(
                recipeName = query,
            )

        emit(DataState.data(data = recipeList))
    }
}
