package de.fhe.ai.weemeal.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.fhe.ai.weemeal.local.dao.RecipeEntityDao
import de.fhe.ai.weemeal.mocks.local.RecipeEntityMock
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import kotlin.test.assertEquals

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See: https://developer.android.com/training/data-storage/room/testing-db
 */
@RunWith(AndroidJUnit4::class)
class RecipeEntityTest {
    private lateinit var recipeEntityDao: RecipeEntityDao
    private lateinit var db: WeeMealDatabase

    // ----------------------------------------------------------------------------------------------
    // SETUP
    // ----------------------------------------------------------------------------------------------
    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, WeeMealDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        recipeEntityDao = db.recipeEntityDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    // ----------------------------------------------------------------------------------------------
    // CREATE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_create_a_list_of_recipes() = runBlocking {
        assertTrue("DB should start empty", recipeEntityDao.getAll().isEmpty())

        val recipeEntityMockList = RecipeEntityMock.generateList()
        recipeEntityMockList.forEach {
            recipeEntityDao.insert(it)
        }

        val recipeList = recipeEntityDao.getAll()
        assertTrue(
            "DB should contain ${recipeEntityMockList.size} entry",
            recipeEntityDao.getAll().size == recipeEntityMockList.size
        )
        recipeList.forEach {
            println(it)
        }
    }

    // ----------------------------------------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_get_a_recipe_by_id() = runBlocking {
        assertTrue("DB should start empty", recipeEntityDao.getAll().isEmpty())

        val recipeEntityMock = RecipeEntityMock.generateSingleObject()

        val loadedEntityId = recipeEntityDao.insert(recipeEntityMock)

        val loadedEntity = recipeEntityDao.get(loadedEntityId)
        assertNotNull("Loaded diary entry should not be null", loadedEntity)

        recipeEntityDao.getAll().forEach {
            println(it)
        }
    }

    @Test
    fun should_find_at_least_one_recipe_by_name() = runBlocking {
        assertTrue("DB should start empty", recipeEntityDao.getAll().isEmpty())

        val recipeEntityMockList = RecipeEntityMock.generateList(amount = 30)
        recipeEntityMockList.forEach {
            recipeEntityDao.insert(it)
        }
        recipeEntityDao.insert(RecipeEntityMock.generateSingleObject(name = "FrontSubstringBack"))

        val recipeEntityListResult = recipeEntityDao.search("Substring")

        assertTrue("Loaded diary entry should not be empty", recipeEntityListResult.isNotEmpty())
        recipeEntityListResult.forEach() {
            println(it)
        }
    }

    // ----------------------------------------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_update_a_recipe() = runBlocking {

        assertTrue("DB should start empty", recipeEntityDao.getAll().isEmpty())

        val recipeEntityMockList = RecipeEntityMock.generateList(amount = 30)
        recipeEntityMockList.forEach {
            recipeEntityDao.insert(it)
        }

        val recipeEntityWhichShouldBeUpdated =
            recipeEntityDao.getAll()[recipeEntityMockList.indices.random()]
        println(recipeEntityWhichShouldBeUpdated)
        val recipeEntityUpdate =
            RecipeEntityMock.generateSingleObject(id = recipeEntityWhichShouldBeUpdated.id)
        println(recipeEntityUpdate)

        recipeEntityDao.update(recipeEntityUpdate)

        val updatedRecipeEntity = recipeEntityDao.get(recipeEntityWhichShouldBeUpdated.id)
        println(updatedRecipeEntity)
        assertEquals(expected = recipeEntityUpdate, actual = updatedRecipeEntity)
    }

    // ----------------------------------------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_delete_entity() = runBlocking {

        assertTrue("DB should start empty", recipeEntityDao.getAll().isEmpty())

        val recipeEntityMockList = RecipeEntityMock.generateList(amount = 30)
        recipeEntityMockList.forEach {
            recipeEntityDao.insert(it)
        }

        val deleteRecipeEntity = recipeEntityDao.getAll().get(recipeEntityMockList.indices.random())

        recipeEntityDao.delete(deleteRecipeEntity)
        assertNull(
            "DB should not contain deleted Entity",
            recipeEntityDao.get(deleteRecipeEntity.id)
        )
        assertTrue(
            "DB should have 1 item less after deletion",
            recipeEntityDao.getAll().size == recipeEntityMockList.size - 1
        )
    }
}
