package de.fhe.ai.weemeal.mocks.domain

import de.fhe.ai.weemeal.common.extentions.measurementUnit
import de.fhe.ai.weemeal.domain.formats.QuantityFormat
import de.fhe.ai.weemeal.domain.models.Ingredient
import io.bloco.faker.Faker

object IngredientMock {
    private val faker: Faker = Faker()
    fun generateSingleObject(): Ingredient {
        return Ingredient(
            name = faker.food.ingredient(),
            image = "",
            quantity = QuantityFormat(
                quantity = faker.number.between(1, 100).toFloat(),
                unit = faker.measurementUnit()
            ),
        )
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<Ingredient> {
        val internalList: MutableList<Ingredient> = mutableListOf()

        for (i in 1..amount!!) {
            internalList.add(generateSingleObject())
        }
        return internalList
    }
}
