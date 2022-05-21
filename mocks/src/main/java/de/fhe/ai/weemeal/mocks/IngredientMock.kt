package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.common.extentions.measurementUnit
import de.fhe.ai.weemeal.common.formats.QuantityFormat
import io.bloco.faker.Faker

object IngredientMock {
    private var faker: Faker = Faker()

    fun generateIngredient(): Ingredient {
        return Ingredient(
            name = faker.food.ingredient(),
            // val image: String? = "",
            quantity = QuantityFormat(
                quantity = faker.number.between(1, 100).toFloat(),
                unit = faker.measurementUnit()
            ),
            //    val nutritionalValue? : ?
        )
    }

    fun generateList(amount: Int): List<Ingredient> {
        assert(amount >= 0)
        val ingredientList: MutableList<Ingredient> = mutableListOf()

        for (i in 1..amount) {
            ingredientList.add(generateIngredient())
        }
        return ingredientList
    }
}
