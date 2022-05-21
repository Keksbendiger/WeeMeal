package de.fhe.ai.weemeal.mocks

import de.fhe.ai.weemeal.domain.models.ShoppingList
import io.bloco.faker.Faker

object ShoppingListMock {
    private var faker: Faker = Faker()

    fun generateShoppingList(): ShoppingList {
        return ShoppingList(
            items = IngredientMock.generateList()
        )
    }
}
