package de.fhe.ai.weemeal.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.fhe.ai.weemeal.local.dao.ShoppingListEntityDao
import de.fhe.ai.weemeal.mocks.local.ShoppingListEntityMock
import java.io.IOException
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See: https://developer.android.com/training/data-storage/room/testing-db
 */
@RunWith(AndroidJUnit4::class)
class ShoppingListEntityTest {
    private lateinit var shoppingListEntityDao: ShoppingListEntityDao
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
        shoppingListEntityDao = db.shoppingListEntityDao()
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
    fun should_create_a_list_of_shoppingLists() = runBlocking {
        assertTrue("DB should start empty", shoppingListEntityDao.getAll().isEmpty())


        val shoppingListEntityMockList = ShoppingListEntityMock.generateList()
        shoppingListEntityMockList.forEach {
            shoppingListEntityDao.insert(it)
        }

        val shoppingListList = shoppingListEntityDao.getAll()
        assertTrue(
            "DB should contain ${shoppingListEntityMockList.size} entry",
            shoppingListEntityDao.getAll().size == shoppingListEntityMockList.size
        )
        shoppingListList.forEach {
            println(it)
        }
    }

    // ----------------------------------------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_get_a_shoppingList_by_id() = runBlocking {
        assertTrue("DB should start empty", shoppingListEntityDao.getAll().isEmpty())

        val shoppingListEntityMock = ShoppingListEntityMock.generateSingleObject()

        val loadedEntityId = shoppingListEntityDao.insert(shoppingListEntityMock)

        val loadedEntity = shoppingListEntityDao.get(loadedEntityId)
        assertNotNull("Loaded diary entry should not be null", loadedEntity)

        shoppingListEntityDao.getAll().forEach {
            println(it)
        }
    }


    // ----------------------------------------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------------------------------------


    // ----------------------------------------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_delete_entity() = runBlocking {

        assertTrue("DB should start empty", shoppingListEntityDao.getAll().isEmpty())

        val shoppingListEntityMockList = ShoppingListEntityMock.generateList(amount = 30)
        shoppingListEntityMockList.forEach {
            shoppingListEntityDao.insert(it)
        }

        val deleteShoppingListEntity =
            shoppingListEntityDao.getAll().get(shoppingListEntityMockList.indices.random())

        shoppingListEntityDao.delete(deleteShoppingListEntity)
        assertNull(
            "DB should not contain deleted Entity",
            shoppingListEntityDao.get(deleteShoppingListEntity.id)
        )
        assertTrue(
            "DB should have 1 item less after deletion",
            shoppingListEntityDao.getAll().size == shoppingListEntityMockList.size - 1
        )
    }
}
