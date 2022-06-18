package de.fhe.ai.weemeal.repository

import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.mocks.domain.MealMock
import de.fhe.ai.weemeal.repository.meal.MealRepository
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MealRepositoryTest : BaseTest() {

    private val mealRepository: MealRepository by inject()

    // ----------------------------------------------------------------------------------------------
    // SETUP
    // ----------------------------------------------------------------------------------------------
    @BeforeTest
    fun createDb() = runTest {
        MealMock.generateList().forEach {
            mealRepository.insertOrUpdateMeal(it)
        }
    }

    @AfterTest
    fun closeDbd() = runTest {
        mealRepository.deleteAllMeals()
    }

    // ----------------------------------------------------------------------------------------------
    // CREATE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_create_a_meals() = runTest {
        val mealMock = MealMock.generateSingleObject()
        val insertedMeal = mealRepository.insertOrUpdateMeal(mealMock)
        assertTrue(mealMock.equalsWithoutId(insertedMeal))

        val loadedMeal = mealRepository.getMeal(insertedMeal!!.internalId)

        assertEquals(insertedMeal, loadedMeal)
        assertTrue(mealMock.equalsWithoutId(loadedMeal))
    }

    // ----------------------------------------------------------------------------------------------
    // READ
    // ----------------------------------------------------------------------------------------------

    @Test
    fun should_get_a_meal_by_id() = runTest {
        val insertedMealList = mutableListOf<Meal>()
        val mealMockList = MealMock.generateList()

        mealMockList.forEach {
            insertedMealList.add(mealRepository.insertOrUpdateMeal(it)!!)
        }

        val index: Int = insertedMealList.size / 2
        val insertedMeal = insertedMealList[index]
        val mealMock = insertedMealList[index]

        assertTrue(mealMock.equalsWithoutId(insertedMeal))

        val loadedMeal = mealRepository.getMeal(insertedMeal.internalId)

        assertEquals(insertedMeal, loadedMeal)
        assertTrue(mealMock.equalsWithoutId(loadedMeal))
    }

    //
    // ----------------------------------------------------------------------------------------------
    // UPDATE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_update_a_meal() = runTest {
        val insertedMealList = mutableListOf<Meal>()
        val mealMockList = MealMock.generateList()

        mealMockList.forEach {
            insertedMealList.add(mealRepository.insertOrUpdateMeal(it)!!)
        }

        val index: Int = insertedMealList.size / 2
        val insertedMeal = insertedMealList[index]

        assertNotNull(mealRepository.getMeal(insertedMeal.internalId))

        val updatedMeal = MealMock.generateSingleObject(internalId = insertedMeal.internalId)
        mealRepository.insertOrUpdateMeal(updatedMeal)
        assertNotNull(mealRepository.getMeal(insertedMeal.internalId))

        assertTrue(updatedMeal.equalsWithoutId(mealRepository.getMeal(insertedMeal.internalId)))
    }

    // ----------------------------------------------------------------------------------------------
    // DELETE
    // ----------------------------------------------------------------------------------------------
    @Test
    fun should_delete_one_meal() = runTest {
        assertTrue(mealRepository.getAll().isNotEmpty())

        val insertedMealList = mutableListOf<Meal>()
        val mealMockList = MealMock.generateList()

        mealMockList.forEach {
            insertedMealList.add(mealRepository.insertOrUpdateMeal(it)!!)
        }

        val index: Int = insertedMealList.size / 2
        val insertedMeal = insertedMealList[index]

        assertNotNull(mealRepository.getMeal(insertedMeal.internalId))
        mealRepository.deleteMeal(insertedMeal)
        assertNull(mealRepository.getMeal(insertedMeal.internalId))
    }

    @Test
    fun should_delete_all_meals() = runTest {
        assertTrue(mealRepository.getAll().isNotEmpty())
        mealRepository.deleteAllMeals()
        assertTrue(mealRepository.getAll().isEmpty())
    }
}
