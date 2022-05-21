package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.common.extentions.measurementUnit
import de.fhe.ai.weemeal.common.formats.QuantityFormat
import de.fhe.ai.weemeal.domain.models.Ingredient
import io.bloco.faker.Faker

object IngredientMock {
    private var faker: Faker = Faker()

    fun generateIngredient(): Ingredient {
        return Ingredient(
            name = faker.food.ingredient(),
            image = "",
            quantity = QuantityFormat(
                quantity = faker.number.between(1, 100).toFloat(),
                unit = faker.measurementUnit()
            ),
            //    val nutritionalValue? : ?
        )
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<Ingredient> {
        val ingredientList: MutableList<Ingredient> = mutableListOf()

        for (i in 1..amount!!) {
            ingredientList.add(generateIngredient())
        }
        return ingredientList
    }
}
