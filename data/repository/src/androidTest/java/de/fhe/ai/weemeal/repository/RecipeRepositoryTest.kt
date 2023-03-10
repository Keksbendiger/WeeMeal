package de.fhe.ai.weemeal.repository

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.mocks.RecipeMock
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import kotlinx.coroutines.flow.first
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class RecipeRepositoryTest : BaseTest() {

    private val recipeRepository: RecipeRepository by inject()

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
    // CREATE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_create_a_recipes() = runTest {
        val recipeMock = RecipeMock.generateSingleObject()
        val insertedRecipe = recipeRepository.insertOrUpdateRecipe(recipeMock)
        assertTrue(recipeMock.equalsWithoutId(insertedRecipe))

        val loadedRecipe = recipeRepository.getRecipe(insertedRecipe!!.internalId)

        assertEquals(insertedRecipe, loadedRecipe)
        assertTrue(recipeMock.equalsWithoutId(loadedRecipe))
    }

    // ----------------------------------------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------------------------------------

    @Test
    fun should_get_a_recipe_by_id() = runTest {
        val insertedRecipeList = mutableListOf<Recipe>()
        val recipeMockList = RecipeMock.generateList()

        recipeMockList.forEach {
            insertedRecipeList.add(recipeRepository.insertOrUpdateRecipe(it)!!)
        }

        val index: Int = insertedRecipeList.size / 2
        val insertedRecipe = insertedRecipeList[index]
        val recipeMock = insertedRecipeList[index]

        assertTrue(recipeMock.equalsWithoutId(insertedRecipe))

        val loadedRecipe = recipeRepository.getRecipe(insertedRecipe.internalId)

        assertEquals(insertedRecipe, loadedRecipe)
        assertTrue(recipeMock.equalsWithoutId(loadedRecipe))
    }

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

        val loadedRecipeList = recipeRepository.searchRecipeByName(insertedRecipe.name).first()
        assertTrue(loadedRecipeList.isNotEmpty())
        loadedRecipeList.forEach() {
            println(it)
        }
    }

    //
    // ----------------------------------------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_update_a_recipe() = runTest {
        val insertedRecipeList = mutableListOf<Recipe>()
        val recipeMockList = RecipeMock.generateList()

        recipeMockList.forEach {
            insertedRecipeList.add(recipeRepository.insertOrUpdateRecipe(it)!!)
        }

        val index: Int = insertedRecipeList.size / 2
        val insertedRecipe = insertedRecipeList[index]

        assertNotNull(recipeRepository.getRecipe(insertedRecipe.internalId))

        val updatedRecipe = RecipeMock.generateSingleObject(internalId = insertedRecipe.internalId)
        recipeRepository.insertOrUpdateRecipe(updatedRecipe)
        assertNotNull(recipeRepository.getRecipe(insertedRecipe.internalId))

        assertTrue(updatedRecipe.equalsWithoutId(recipeRepository.getRecipe(insertedRecipe.internalId)))
    }

    // ----------------------------------------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_delete_one_recipe() = runTest {
        assertTrue(recipeRepository.getAll().isNotEmpty())

        val insertedRecipeList = mutableListOf<Recipe>()
        val recipeMockList = RecipeMock.generateList()

        recipeMockList.forEach {
            insertedRecipeList.add(recipeRepository.insertOrUpdateRecipe(it)!!)
        }

        val index: Int = insertedRecipeList.size / 2
        val insertedRecipe = insertedRecipeList[index]

        assertNotNull(recipeRepository.getRecipe(insertedRecipe.internalId))
        recipeRepository.deleteRecipe(insertedRecipe)
        assertNull(recipeRepository.getRecipe(insertedRecipe.internalId))
    }

    @Test
    fun should_delete_all_recipes() = runTest {
        assertTrue(recipeRepository.getAll().isNotEmpty())
        recipeRepository.deleteAllRecipes()
        assertTrue(recipeRepository.getAll().isEmpty())
    }
}
