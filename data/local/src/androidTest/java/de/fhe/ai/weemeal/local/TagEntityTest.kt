package de.fhe.ai.weemeal.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.fhe.ai.weemeal.local.dao.TagEntityDao
import de.fhe.ai.weemeal.mocks.local.TagEntityMock
import java.io.IOException
import kotlin.test.assertEquals
import kotlinx.coroutines.flow.first
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
class TagEntityTest {
    private lateinit var tagEntityDao: TagEntityDao
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
        tagEntityDao = db.tagEntityDao()
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
    fun should_create_a_list_of_tags() = runBlocking {
        assertTrue("DB should start empty", tagEntityDao.getAll().isEmpty())

        val tagEntityMockList = TagEntityMock.generateList()
        tagEntityMockList.forEach {
            tagEntityDao.insert(it)
        }

        val tagList = tagEntityDao.getAll()
        assertTrue(
            "DB should contain ${tagEntityMockList.size} entry",
            tagEntityDao.getAll().size == tagEntityMockList.size
        )
        tagList.forEach {
            println(it)
        }
    }

    // ----------------------------------------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_get_a_tag_by_id() = runBlocking {
        assertTrue("DB should start empty", tagEntityDao.getAll().isEmpty())

        val tagEntityMock = TagEntityMock.generateSingleObject()

        val loadedEntityId = tagEntityDao.insert(tagEntityMock)

        val loadedEntity = tagEntityDao.get(loadedEntityId)
        assertNotNull("Loaded diary entry should not be null", loadedEntity)

        tagEntityDao.getAll().forEach {
            println(it)
        }
    }

    @Test
    fun should_find_at_least_one_tag_by_name() = runBlocking {
        assertTrue("DB should start empty", tagEntityDao.getAll().isEmpty())

        val tagEntityMockList = TagEntityMock.generateList(amount = 30)
        tagEntityMockList.forEach {
            tagEntityDao.insert(it)
        }
        tagEntityDao.insert(TagEntityMock.generateSingleObject(name = "FrontSubstringBack"))

        val tagEntityListResult = tagEntityDao.search("Substring").first()

        assertTrue(
            "Loaded diary entry should not be empty",
            tagEntityListResult.isNotEmpty()
        )
        tagEntityListResult.forEach() {
            println(it)
        }
    }

    // ----------------------------------------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_update_a_tag() = runBlocking {

        assertTrue("DB should start empty", tagEntityDao.getAll().isEmpty())

        val tagEntityMockList = TagEntityMock.generateList(amount = 30)
        tagEntityMockList.forEach {
            tagEntityDao.insert(it)
        }

        val tagEntityWhichShouldBeUpdated =
            tagEntityDao.getAll()[tagEntityMockList.indices.random()]
        println(tagEntityWhichShouldBeUpdated)
        val tagEntityUpdate =
            TagEntityMock.generateSingleObject(id = tagEntityWhichShouldBeUpdated.id)
        println(tagEntityUpdate)

        tagEntityDao.update(tagEntityUpdate)

        val updatedTagEntity =
            tagEntityDao.get(tagEntityWhichShouldBeUpdated.id)
        println(updatedTagEntity)
        assertEquals(expected = tagEntityUpdate, actual = updatedTagEntity)
    }

    // ----------------------------------------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_delete_entity() = runBlocking {

        assertTrue("DB should start empty", tagEntityDao.getAll().isEmpty())

        val tagEntityMockList = TagEntityMock.generateList(amount = 30)
        tagEntityMockList.forEach {
            tagEntityDao.insert(it)
        }

        val deleteTagEntity =
            tagEntityDao.getAll().get(tagEntityMockList.indices.random())

        tagEntityDao.delete(deleteTagEntity)
        assertNull(
            "DB should not contain deleted Entity",
            tagEntityDao.get(deleteTagEntity.id)
        )
        assertTrue(
            "DB should have 1 item less after deletion",
            tagEntityDao.getAll().size == tagEntityMockList.size - 1
        )
    }
}
