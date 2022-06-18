package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.common.extentions.timeUnit
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.mocks.domain.IngredientMock
import de.fhe.ai.weemeal.mocks.domain.TagMock
import io.bloco.faker.Faker

object RecipeMock {
    private var faker: Faker = Faker()

    fun generateSingleObject(internalId: Long = 0): Recipe {
        return Recipe(
            internalId = internalId,
            name = faker.food.dish(),
            defaultServings = faker.number.between(1, 10),
            defaultIngredients = IngredientMock.generateList(faker.number.between(1, 20)),
            timePreparation = TimeFormat(
                value = faker.number.between(1, 60).toFloat(),
                unit = faker.timeUnit()
            ),
            timeActiveCooking = TimeFormat(
                value = faker.number.between(1, 60).toFloat(),
                unit = faker.timeUnit()
            ),
            timeOverall = TimeFormat(
                value = faker.number.between(1, 60).toFloat(),
                unit = faker.timeUnit()
            ),
            instructions = faker.lorem.paragraph(faker.number.between(1, 20), true),
            image = de.fhe.ai.weemeal.R.drawable.recipe_placeholder,
            tags = TagMock.generateList(),
        )
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<Recipe> {

        val recipeList: MutableList<Recipe> = mutableListOf()

        for (i in 1..amount!!) {
            recipeList.add(generateSingleObject())
        }
        return recipeList
    }
}
