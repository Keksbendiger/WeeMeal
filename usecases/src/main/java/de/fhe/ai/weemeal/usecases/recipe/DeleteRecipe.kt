package de.fhe.ai.weemeal.usecases.recipe

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DeleteRecipe : KoinComponent {

    private val recipeRepository: RecipeRepository by inject()

    /**
     * Saves or updates the given Recipe in the DB
     *
     * @return void
     */
    suspend fun execute(recipe: Recipe) {
        recipeRepository.deleteRecipe(recipe)
    }
}
