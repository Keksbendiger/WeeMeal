package de.fhe.ai.weemeal.usecases.usabilityTest

import de.fhe.ai.weemeal.mocks.RecipeMock
import de.fhe.ai.weemeal.repository.meal.MealRepository
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PrepareHCIUsabilityTest : KoinComponent {

    private val recipeRepository: RecipeRepository by inject()
    private val mealRepository: MealRepository by inject()

    /**
     * Prepares the App for a coming Usability Test:
     * Deletes all existing Meals and Recipes and creates 10 new recipes
     * From those Recipes are then created 5 Meals and added to a Day this week
     */
    suspend fun execute() {
        mealRepository.deleteAllMeals()
        recipeRepository.deleteAllRecipes()
        val recipes = RecipeMock.generateList(10)
        for (recipe in recipes) {
            recipeRepository.insertOrUpdateRecipe(recipe)
        }
        // TODO prepare meals
    }
}
