package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.common.extentions.timeUnit
import de.fhe.ai.weemeal.domain.enums.TimeUnit
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.local.entity.RecipeEntity
import io.bloco.faker.Faker

object RecipeMock {
    private var faker: Faker = Faker()

    fun generateRecipe(): Recipe {
        return Recipe(
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

        val recipeList: MutableList<Recipe> = mutableListOf()

        for (i in 1..amount!!) {
            recipeList.add(generateRecipe())
        }
        return recipeList
    }


    fun generateRecipeEntity(
        name: String = faker.food.dish(),
        defaultServings: Int? = faker.number.between(1, 10),
        timePreparation: TimeFormat? = TimeFormat(
            value = faker.number.between(1, 60).toFloat(),
            unit = faker.timeUnit()
        ),
        timeActiveCooking: TimeFormat? = TimeFormat(
            value = faker.number.between(1, 60).toFloat(),
            unit = faker.timeUnit()
        ),
        timeOverall: TimeFormat? = TimeFormat(
            value = faker.number.between(1, 60).toFloat(),
            unit = faker.timeUnit()
        ),
        instructions: String? = faker.lorem.paragraph(faker.number.between(1, 20), true),
        image: String? = "",
    ): RecipeEntity {
        return RecipeEntity(
            name = name,
            defaultServings = defaultServings,
            timePreparation = timePreparation,
            timeActiveCooking = timeActiveCooking,
            timeOverall = timeOverall,
            instructions = instructions,
            image = image,
        )
    }

    fun generateEntityList(amount: Int? = faker.number.between(1, 30)): List<RecipeEntity> {

        val recipeList: MutableList<RecipeEntity> = mutableListOf()

        for (i in 1..amount!!) {
            recipeList.add(generateRecipeEntity())
        }
        return recipeList
    }
}
