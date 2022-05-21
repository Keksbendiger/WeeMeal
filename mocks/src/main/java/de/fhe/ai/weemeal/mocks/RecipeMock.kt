package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.domain.models.Recipe
import io.bloco.faker.Faker

object RecipeMock {
    private var faker: Faker = Faker()

    fun generateRecipe(): Recipe {
        return Recipe(
            name = faker.food.dish(),
            defaultServings = faker.number.between(1, 10),
            defaultIngredients = IngredientMock.generateList(faker.number.between(1, 20)),
//        timePreparation = TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
//        timeActiveCooking = TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
//        timeOverall = TimeFormat? = TimeFormat(value = 0.0f, unit = "h"),
            instructions = faker.lorem.paragraph(faker.number.between(1, 20), true),
//        image = String? = "",
//        seasonality = List<String>? = listOf(),
            mealTime = MealTimeMock.generateList(faker.number.between(1, 5)),
//        optionalTags = List<Tag>? = listOf(),
//    -nutritionalValue : ?,

        )
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<Recipe> {

        val recipeList: MutableList<Recipe> = mutableListOf()

        for (i in 1..amount!!) {
            recipeList.add(generateRecipe())
        }
        return recipeList
    }
}
