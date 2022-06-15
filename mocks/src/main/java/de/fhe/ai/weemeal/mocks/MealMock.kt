package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.domain.enums.CookColor
import de.fhe.ai.weemeal.domain.models.Meal
import io.bloco.faker.Faker

object MealMock {
    private val faker: Faker = Faker()
    fun generateSingleObject(): Meal {
        return Meal(
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
}
