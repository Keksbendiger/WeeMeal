package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.common.extentions.timeUnit
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.local.entity.RecipeEntity
import io.bloco.faker.Faker

object RecipeEntityMock {
    private val faker: Faker = Faker()

    fun generateSingleObject(
        id: Long = 0,
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
            id = id,
            name = name,
            defaultServings = defaultServings,
            timePreparation = timePreparation,
            timeActiveCooking = timeActiveCooking,
            timeOverall = timeOverall,
            instructions = instructions,
            image = image,
        )
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<RecipeEntity> {

        val internalList: MutableList<RecipeEntity> = mutableListOf()

        for (i in 1..amount!!) {
            internalList.add(generateSingleObject())
        }
        return internalList
    }
}
