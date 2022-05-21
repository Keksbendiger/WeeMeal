package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.domain.enums.CookColor
import io.bloco.faker.Faker

object MealMock {
    private var faker: Faker = Faker()

    fun generateMeal(): Meal {
        return Meal(
            recipe = RecipeMock.generateRecipe(),
            servings = faker.number.between(1, 10),
            cookColor = CookColor.getRandom(),
            cookingDate = faker.date.forward(),
            shoppingListCreatedAt = faker.date.forward(),
        )
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<Meal> {

        val recipeList: MutableList<Meal> = mutableListOf()

        for (i in 1..amount!!) {
            recipeList.add(generateMeal())
        }
        return recipeList
    }
}
