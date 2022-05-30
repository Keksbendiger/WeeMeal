package de.fhe.ai.weemeal.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.fhe.ai.weemeal.common.extentions.timeUnit
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.local.dao.RecipeEntityDao
import de.fhe.ai.weemeal.local.entity.RecipeEntity
import de.fhe.ai.weemeal.mocks.RecipeMock
import io.bloco.faker.Faker
import java.io.IOException
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
class RoomDbTest {
    private var faker: Faker = Faker()
    private lateinit var recipeEntityDao: RecipeEntityDao
    private lateinit var db: WeeMealDatabase

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


    @Test
    fun should_create_a_list_of_recipes() = runBlocking {
        assertTrue("DB should start empty", recipeEntityDao.getAll().isEmpty())

        val recipeEntityMockList = RecipeMock.generateEntityList()
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

    @Test
    fun should_get_a_recipe_by_id() = runBlocking {
        assertTrue("DB should start empty", recipeEntityDao.getAll().isEmpty())

        val recipeEntityMock = RecipeMock.generateRecipeEntity()

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

        val recipeEntityMockList = RecipeMock.generateEntityList(amount = 30)
        recipeEntityMockList.forEach {
            recipeEntityDao.insert(it)
        }
        recipeEntityDao.insert(RecipeMock.generateRecipeEntity(name="FrontSubstringBack"))

        val recipeEntityListResult = recipeEntityDao.search("Substring").first()

        assertTrue("Loaded diary entry should not be empty", recipeEntityListResult.isNotEmpty())
        recipeEntityListResult.forEach() {
            println(it)
        }
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

        assertTrue("DB should start empty", recipeEntityDao.getAll().isEmpty())

        val entityId = recipeEntityDao.insert(prepareUserEntity())
        assertTrue("DB should contain one entry", recipeEntityDao.getAll().size == 1)

        val loadedEntity = recipeEntityDao.get(entityId)
        assertNotNull("Loaded diary entry should not be null", loadedEntity)

        recipeEntityDao.delete(loadedEntity!!)
        assertNull("DB should not contain deleted Entity", recipeEntityDao.get(entityId))
        assertTrue("DB should be empty after deletion", recipeEntityDao.getAll().isEmpty())
    }

    private fun prepareUserEntity(): RecipeEntity {
        return RecipeEntity(name = "Steffen")
    }
}
