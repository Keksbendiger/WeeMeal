package de.fhe.ai.weemeal.usecases.recipe

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class GetRecipes : KoinComponent {

    private val recipeRepository: RecipeRepository by inject()

    /**
     * TODO
     *
     * @return DataState
     */
    suspend fun execute(): List<Recipe> {

        return recipeRepository.getAll()
    }
}
