package de.fhe.ai.weemeal.repository

import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.mocks.RecipeMock
import de.fhe.ai.weemeal.mocks.domain.ShoppingListMock
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import de.fhe.ai.weemeal.repository.shoppingList.ShoppingListRepository
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import org.koin.test.inject

class ShoppingListRepositoryTest : BaseTest() {

    private val shoppingListRepository: ShoppingListRepository by inject()
    private val recipeRepository: RecipeRepository by inject()

    // ----------------------------------------------------------------------------------------------
    // SETUP
    // ----------------------------------------------------------------------------------------------
    @BeforeTest
    fun createDb() = runTest {
        val recipeMock = RecipeMock.generateList()

        val ingredientList: MutableList<Ingredient> = mutableListOf()

        recipeMock.forEach { recipe ->
            recipeRepository.insertOrUpdateRecipe(recipe)
            recipe.defaultIngredients?.forEach { ingredient ->
                ingredientList.add(ingredient)
            }
        }


        ShoppingListMock.generateList(ingredientList = ingredientList).let {
            shoppingListRepository.insertShoppingList(it)
        }
    }

    @AfterTest
    fun closeDbd() = runTest {
        shoppingListRepository.deleteShoppingList()
    }

    // ----------------------------------------------------------------------------------------------
    // CREATE
    // ----------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------------------------------------


    @Test
    fun should_create_and_get_a_shoppingLists() = runTest {

        val recipeMock = RecipeMock.generateList(2)

        val ingredientList: MutableList<Ingredient> = mutableListOf()

        recipeMock.forEach { recipe ->
            recipeRepository.insertOrUpdateRecipe(recipe)
            recipe.defaultIngredients?.forEach { ingredient ->
                ingredientList.add(ingredient)
            }
        }


        val shoppingListMock = ShoppingListMock.generateList(ingredientList = ingredientList)
        shoppingListRepository.insertShoppingList(shoppingListMock)
        val loadedShoppingList = shoppingListRepository.getShoppingList()

        assertTrue(shoppingListMock.equalsWithoutId(loadedShoppingList) )

    }


    //
    // ----------------------------------------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------------------------------------

    // ----------------------------------------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_delete_the_shoppingLists() = runTest {
        assertTrue(shoppingListRepository.getShoppingList() != null)
        shoppingListRepository.deleteShoppingList()
        assertTrue(shoppingListRepository.getShoppingList() == null)
    }
}
