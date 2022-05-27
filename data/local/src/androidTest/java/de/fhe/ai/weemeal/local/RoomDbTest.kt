package de.fhe.ai.pmc.acat.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.fhe.ai.weemeal.local.WeeMealDatabase
import de.fhe.ai.weemeal.local.dao.RecipeEntityDao
import de.fhe.ai.weemeal.local.entity.RecipeEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See: https://developer.android.com/training/data-storage/room/testing-db
 */
@RunWith(AndroidJUnit4::class)
class RoomDbTest {

    private lateinit var userEntityDao: RecipeEntityDao
    private lateinit var db: WeeMealDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, WeeMealDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userEntityDao = db.recipeEntityDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun create() {
    }

    @Test
    fun read() {
    }

    @Test
    fun update() {
    }

    @Test
    fun delete() {
    }

    @Test
    fun writeReadDeleteSingleDiaryEntry() = runBlocking {

        assertTrue("DB should start empty", userEntityDao.getAll().isEmpty())

        val entityId = userEntityDao.insert(prepareUserEntity())
        assertTrue("DB should contain one entry", userEntityDao.getAll().size == 1)

        val loadedEntity = userEntityDao.get(entityId)
        assertNotNull("Loaded diary entry should not be null", loadedEntity)

        userEntityDao.delete(loadedEntity!!)
        assertNull("DB should not contain deleted Entity", userEntityDao.get(entityId))
        assertTrue("DB should be empty after deletion", userEntityDao.getAll().isEmpty())
    }

    private fun prepareUserEntity(): RecipeEntity {
        return RecipeEntity(name = "Steffen")
    }
}
