package de.fhe.ai.weemeal.mocks.domain

import de.fhe.ai.weemeal.common.extentions.timeUnit
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.models.Recipe
import io.bloco.faker.Faker

object RecipeMock {
    private val faker: Faker = Faker()

    fun generateRecipe(internalId: Long = 0): Recipe { // TODO rename to generateSingleObject
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
            image = "",
            tags = TagMock.generateList(),
        )
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<Recipe> {

        val internalList: MutableList<Recipe> = mutableListOf()

        for (i in 1..amount!!) {
            internalList.add(generateRecipe())
        }
        return internalList
    }
}
