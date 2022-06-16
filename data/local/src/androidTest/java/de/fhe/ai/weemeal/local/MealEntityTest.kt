package de.fhe.ai.weemeal.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.fhe.ai.weemeal.local.dao.MealEntityDao
import de.fhe.ai.weemeal.mocks.local.MealEntityMock
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
class MealEntityTest {
    private lateinit var mealEntityDao: MealEntityDao
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
        mealEntityDao = db.mealEntityDao()
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
    fun should_create_a_list_of_meals() = runBlocking {
        assertTrue("DB should start empty", mealEntityDao.getAll().isEmpty())

        val mealEntityMockList = MealEntityMock.generateList()
        mealEntityMockList.forEach {
            mealEntityDao.insert(it)
        }

        val mealList = mealEntityDao.getAll()
        assertTrue(
            "DB should contain ${mealEntityMockList.size} entry",
            mealEntityDao.getAll().size == mealEntityMockList.size
        )
        mealList.forEach {
            println(it)
        }
    }

    // ----------------------------------------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_get_a_meal_by_id() = runBlocking {
        assertTrue("DB should start empty", mealEntityDao.getAll().isEmpty())

        val mealEntityMock = MealEntityMock.generateSingleObject()

        val loadedEntityId = mealEntityDao.insert(mealEntityMock)

        val loadedEntity = mealEntityDao.get(loadedEntityId)
        assertNotNull("Loaded diary entry should not be null", loadedEntity)

        mealEntityDao.getAll().forEach {
            println(it)
        }
    }

    // ----------------------------------------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_update_a_meal() = runBlocking {

        assertTrue("DB should start empty", mealEntityDao.getAll().isEmpty())

        val mealEntityMockList = MealEntityMock.generateList(amount = 30)
        mealEntityMockList.forEach {
            mealEntityDao.insert(it)
        }

        val mealEntityWhichShouldBeUpdated =
            mealEntityDao.getAll()[mealEntityMockList.indices.random()]
        println(mealEntityWhichShouldBeUpdated)
        val mealEntityUpdate =
            MealEntityMock.generateSingleObject(id = mealEntityWhichShouldBeUpdated.id)
        println(mealEntityUpdate)

        mealEntityDao.update(mealEntityUpdate)

        val updatedMealEntity = mealEntityDao.get(mealEntityWhichShouldBeUpdated.id)
        println(updatedMealEntity)
        assertEquals(expected = mealEntityUpdate, actual = updatedMealEntity)
    }

    // ----------------------------------------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_delete_entity() = runBlocking {

        assertTrue("DB should start empty", mealEntityDao.getAll().isEmpty())

        val mealEntityMockList = MealEntityMock.generateList(amount = 30)
        mealEntityMockList.forEach {
            mealEntityDao.insert(it)
        }

        val deleteMealEntity = mealEntityDao.getAll().get(mealEntityMockList.indices.random())

        mealEntityDao.delete(deleteMealEntity)
        assertNull(
            "DB should not contain deleted Entity",
            mealEntityDao.get(deleteMealEntity.id)
        )
        assertTrue(
            "DB should have 1 item less after deletion",
            mealEntityDao.getAll().size == mealEntityMockList.size - 1
        )
    }
}
