package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.common.extentions.measurementUnit
import de.fhe.ai.weemeal.common.formats.QuantityFormat
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.ShoppingList
import io.bloco.faker.Faker

object ShoppingListMock {
    private var faker: Faker = Faker()

    fun generateShoppingList(): ShoppingList {
        return ShoppingList(
            items= IngredientMock.generateList()
        )
    }
}
