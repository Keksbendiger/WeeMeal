package de.fhe.ai.weemeal.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.fhe.ai.weemeal.local.dao.IngredientEntityDao
import de.fhe.ai.weemeal.mocks.local.IngredientEntityMock
import kotlinx.coroutines.flow.first
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
class IngredientEntityTest {
    private lateinit var ingredientEntityDao: IngredientEntityDao
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
        ingredientEntityDao = db.ingredientEntityDao()
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
    fun should_create_a_list_of_ingredients() = runBlocking {
        assertTrue("DB should start empty", ingredientEntityDao.getAll().isEmpty())

        val ingredientEntityMockList = IngredientEntityMock.generateList()
        ingredientEntityMockList.forEach {
            ingredientEntityDao.insert(it)
        }

        val ingredientList = ingredientEntityDao.getAll()
        assertTrue(
            "DB should contain ${ingredientEntityMockList.size} entry",
            ingredientEntityDao.getAll().size == ingredientEntityMockList.size
        )
        ingredientList.forEach {
            println(it)
        }
    }

    // ----------------------------------------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_get_a_ingredient_by_id() = runBlocking {
        assertTrue("DB should start empty", ingredientEntityDao.getAll().isEmpty())

        val ingredientEntityMock = IngredientEntityMock.generateSingleObject()

        val loadedEntityId = ingredientEntityDao.insert(ingredientEntityMock)

        val loadedEntity = ingredientEntityDao.get(loadedEntityId)
        assertNotNull("Loaded diary entry should not be null", loadedEntity)

        ingredientEntityDao.getAll().forEach {
            println(it)
        }
    }

    @Test
    fun should_find_at_least_one_ingredient_by_name() = runBlocking {
        assertTrue("DB should start empty", ingredientEntityDao.getAll().isEmpty())

        val ingredientEntityMockList = IngredientEntityMock.generateList(amount = 30)
        ingredientEntityMockList.forEach {
            ingredientEntityDao.insert(it)
        }
        ingredientEntityDao.insert(IngredientEntityMock.generateSingleObject(name = "FrontSubstringBack"))

        val ingredientEntityListResult = ingredientEntityDao.search("Substring").first()

        assertTrue(
            "Loaded diary entry should not be empty",
            ingredientEntityListResult.isNotEmpty()
        )
        ingredientEntityListResult.forEach() {
            println(it)
        }
    }

    // ----------------------------------------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_update_a_ingredient() = runBlocking {

        assertTrue("DB should start empty", ingredientEntityDao.getAll().isEmpty())

        val ingredientEntityMockList = IngredientEntityMock.generateList(amount = 30)
        ingredientEntityMockList.forEach {
            ingredientEntityDao.insert(it)
        }

        val ingredientEntityWhichShouldBeUpdated =
            ingredientEntityDao.getAll()[ingredientEntityMockList.indices.random()]
        println(ingredientEntityWhichShouldBeUpdated)
        val ingredientEntityUpdate =
            IngredientEntityMock.generateSingleObject(id = ingredientEntityWhichShouldBeUpdated.id)
        println(ingredientEntityUpdate)

        ingredientEntityDao.update(ingredientEntityUpdate)

        val updatedIngredientEntity =
            ingredientEntityDao.get(ingredientEntityWhichShouldBeUpdated.id)
        println(updatedIngredientEntity)
        assertEquals(expected = ingredientEntityUpdate, actual = updatedIngredientEntity)
    }

    // ----------------------------------------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_delete_entity() = runBlocking {

        assertTrue("DB should start empty", ingredientEntityDao.getAll().isEmpty())

        val ingredientEntityMockList = IngredientEntityMock.generateList(amount = 30)
        ingredientEntityMockList.forEach {
            ingredientEntityDao.insert(it)
        }

        val deleteIngredientEntity =
            ingredientEntityDao.getAll().get(ingredientEntityMockList.indices.random())

        ingredientEntityDao.delete(deleteIngredientEntity)
        assertNull(
            "DB should not contain deleted Entity",
            ingredientEntityDao.get(deleteIngredientEntity.id)
        )
        assertTrue(
            "DB should have 1 item less after deletion",
            ingredientEntityDao.getAll().size == ingredientEntityMockList.size - 1
        )
    }
}
