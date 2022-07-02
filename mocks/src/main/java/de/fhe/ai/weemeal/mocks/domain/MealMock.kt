package de.fhe.ai.weemeal.mocks.domain

import de.fhe.ai.weemeal.domain.enums.CookColor
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.mocks.RecipeMock
import io.bloco.faker.Faker
import java.util.Calendar
import java.util.Date

object MealMock {
    private val faker: Faker = Faker()

    fun generateSingleObject(internalId: Long = 0): Meal {
        return Meal(
            internalId = internalId,
            recipe = RecipeMock.generateSingleObject(),
            servings = faker.number.between(1, 10),
            cookColor = CookColor.getRandom(),
            cookingDate = faker.date.forward(),
            shoppingListCreatedAt = faker.date.forward(),
        )
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<Meal> {

        val internalList: MutableList<Meal> = mutableListOf()

        for (i in 1..amount!!) {
            internalList.add(generateSingleObject())
        }
        return internalList
    }

    fun generateWeek(amount: Int? = faker.number.between(7,21)): List<Meal>{

        val internalList: MutableList<Meal> = mutableListOf()
        for (i in 1..amount!!) {
            internalList.add(generateWeekListObject())
        }
        return internalList
    }

    private fun generateWeekListObject(internalId: Long = 0): Meal {
        return Meal(
            internalId = internalId,
            recipe = RecipeMock.generateSingleObject(),
            servings = faker.number.between(1, 10),
            cookColor = CookColor.getRandom(),
            cookingDate = faker.date.between(getDaysAhead(0), getDaysAhead(14)),
            shoppingListCreatedAt = faker.date.forward(),
        )
    }

    fun getDaysAhead(daysAhead: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, daysAhead)

        return calendar.time
    }


}
