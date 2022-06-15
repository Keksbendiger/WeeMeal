package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.domain.models.ShoppingList
import io.bloco.faker.Faker

object ShoppingListMock  {
    private val faker: Faker = Faker()
    fun generateList(): ShoppingList {
        return ShoppingList(
            items = IngredientMock.generateList()
        )
    }
}
