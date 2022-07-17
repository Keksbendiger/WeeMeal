package de.fhe.ai.weemeal.usecases.recipe

import de.fhe.ai.weemeal.common.DataState
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveRecipe : KoinComponent {

    private val recipeRepository: RecipeRepository by inject()

    /**
     * TODO
     *
     * @return DataState
     */
    fun execute(recipe: Recipe): Flow<DataState<Recipe>> = flow {

        emit(DataState.loading())

        val savedRecipe = recipeRepository.insertOrUpdateRecipe(recipe)
        emit(DataState.data(data = savedRecipe))
    }
}
