package de.fhe.ai.weemeal.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.fhe.ai.weemeal.domain.Repository
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

    private lateinit var userEntityDao: UserEntityDao
    private lateinit var db: AppDatabase
    private lateinit var repository: Repository

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        userEntityDao = db.userEntityDao()
        repository = RepositoryImpl(userEntityDao)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
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

    private fun prepareUserEntity(): UserEntity {
        return UserEntity("Steffen")
    }
}
