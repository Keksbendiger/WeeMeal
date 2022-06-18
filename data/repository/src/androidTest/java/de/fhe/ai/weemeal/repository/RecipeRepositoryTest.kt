package de.fhe.ai.weemeal.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import de.fhe.ai.weemeal.mocks.RecipeMock
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See: https://developer.android.com/training/data-storage/room/testing-db
 */
@RunWith(AndroidJUnit4::class)
class RecipeRepositoryTest : BaseTest() {

    private val recipeRepository: RecipeRepository by inject()

//    // ----------------------------------------------------------------------------------------------
//    // SETUP
//    // ----------------------------------------------------------------------------------------------
//    @Before
//    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
//        db = Room.inMemoryDatabaseBuilder(context, WeeMealDatabase::class.java)
//            .allowMainThreadQueries()
//            .build()
//        recipeEntityDao = db.recipeEntityDao()
//    }


    //
//    @After
//    @Throws(IOException::class)
//    fun closeDb() {
//        db.close()
//    }
//

    // ----------------------------------------------------------------------------------------------
    // CREATE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_create_a_recipes() = runBlocking {
        var id: Long = 1
        while (recipeRepository.getRecipe(id) != null) {
            id++
        }

        val testRecipe = RecipeMock.generateSingleObject(id)
        val insertedRecipe = recipeRepository.insertOrUpdateRecipe(testRecipe) //insertOrUpdate
        val loadedRecipe = recipeRepository.getRecipe(id)
        assertEquals(insertedRecipe, loadedRecipe)
        assertTrue(testRecipe.equalsWithoutId(loadedRecipe))
    }

    // ----------------------------------------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------------------------------------

    @Test
    fun should_get_a_recipe_by_id() = runBlocking {

        RecipeMock.generateList().forEach {
            recipeRepository.insertOrUpdateRecipe(it)
        }

        var id: Long = 1
        while (recipeRepository.getRecipe(id) != null) {
            id++
        }

        val testRecipe = RecipeMock.generateSingleObject(id)
        val insertedRecipe = recipeRepository.insertOrUpdateRecipe(testRecipe) //insertOrUpdate
        val loadedRecipe = recipeRepository.getRecipe(id)
        assertEquals(insertedRecipe, loadedRecipe)
        assertTrue(testRecipe.equalsWithoutId(loadedRecipe))
    }

    @Test
    fun should_find_at_least_one_recipe_by_name() = runBlocking {

        var id: Long = 1
        while (recipeRepository.getRecipe(id) != null) {
            id++
        }

        val testRecipe = RecipeMock.generateSingleObject(id)
        val insertedRecipe = recipeRepository.insertOrUpdateRecipe(testRecipe) //insertOrUpdate
        val loadedRecipeList = recipeRepository.searchRecipeByName(testRecipe.name).first()
        assertTrue(loadedRecipeList.isNotEmpty())
        loadedRecipeList.forEach() {
            println(it)
        }
    }


//
//    // ----------------------------------------------------------------------------------------------
//    // UPDATE
//    // ----------------------------------------------------------------------------------------------
//    @Test
//    fun should_update_a_recipe() = runBlocking {
//
//        assertTrue("DB should start empty", recipeEntityDao.getAll().isEmpty())
//
//        val recipeEntityMockList = RecipeEntityMock.generateList(amount = 30)
//        recipeEntityMockList.forEach {
//            recipeEntityDao.insert(it)
//        }
//
//        val recipeEntityWhichShouldBeUpdated =
//            recipeEntityDao.getAll()[recipeEntityMockList.indices.random()]
//        println(recipeEntityWhichShouldBeUpdated)
//        val recipeEntityUpdate =
//            RecipeEntityMock.generateSingleObject(id = recipeEntityWhichShouldBeUpdated.id)
//        println(recipeEntityUpdate)
//
//        recipeEntityDao.update(recipeEntityUpdate)
//
//        val updatedRecipeEntity = recipeEntityDao.get(recipeEntityWhichShouldBeUpdated.id)
//        println(updatedRecipeEntity)
//        assertEquals(expected = recipeEntityUpdate, actual = updatedRecipeEntity)
//    }
//
//    // ----------------------------------------------------------------------------------------------
//    // DELETE
//    // ----------------------------------------------------------------------------------------------
//    @Test
//    fun should_delete_entity() = runBlocking {
//
//        assertTrue("DB should start empty", recipeEntityDao.getAll().isEmpty())
//
//        val recipeEntityMockList = RecipeEntityMock.generateList(amount = 30)
//        recipeEntityMockList.forEach {
//            recipeEntityDao.insert(it)
//        }
//
//        val deleteRecipeEntity = recipeEntityDao.getAll().get(recipeEntityMockList.indices.random())
//
//        recipeEntityDao.delete(deleteRecipeEntity)
//        assertNull(
//            "DB should not contain deleted Entity",
//            recipeEntityDao.get(deleteRecipeEntity.id)
//        )
//        assertTrue(
//            "DB should have 1 item less after deletion",
//            recipeEntityDao.getAll().size == recipeEntityMockList.size - 1
//        )
//    }
}
