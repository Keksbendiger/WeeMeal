package de.fhe.ai.weemeal.usecases

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.mocks.RecipeMock
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class RecipeUseCaseTest : BaseTest() {

    private val recipeRepository: RecipeRepository by inject()
    private val searchRecipeUseCase: SearchRecipes by inject()

    // ----------------------------------------------------------------------------------------------
    // SETUP
    // ----------------------------------------------------------------------------------------------
    @BeforeTest
    fun createDb() = runTest {
        RecipeMock.generateList().forEach {
            recipeRepository.insertOrUpdateRecipe(it)
        }
    }

    @AfterTest
    fun closeDbd() = runTest {
        recipeRepository.deleteAllRecipes()
    }

    // ----------------------------------------------------------------------------------------------
    // Use Case Tests
    // ----------------------------------------------------------------------------------------------

    @Test
    fun should_find_at_least_one_recipe_by_name() = runTest {

        val insertedRecipeList = mutableListOf<Recipe>()
        val recipeMockList = RecipeMock.generateList()

        recipeMockList.forEach {
            insertedRecipeList.add(recipeRepository.insertOrUpdateRecipe(it)!!)
        }

        val index: Int = insertedRecipeList.size / 2
        val insertedRecipe = insertedRecipeList[index]
        val recipeMock = insertedRecipeList[index]

        assertTrue(recipeMock.equalsWithoutId(insertedRecipe))
        println(insertedRecipe)

        val loadedRecipeList = searchRecipeUseCase.execute(insertedRecipe.name)
        assert(loadedRecipeList.count() > 1)
        assertTrue(loadedRecipeList.drop(0).first().isLoading)
        assertFalse(loadedRecipeList.drop(1).first().isLoading)
        assert(loadedRecipeList.drop(1).first().data!!.first().equalsWithoutId(insertedRecipe))
    }
}
