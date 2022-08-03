package de.fhe.ai.weemeal.mocks.domain

import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.ShoppingList
import io.bloco.faker.Faker

object ShoppingListMock {
    private val faker: Faker = Faker()

    fun generateList(
        ingredientList : List<Ingredient> = IngredientMock.generateList(),
    ): ShoppingList {
        return ShoppingList(
            items = ingredientList
        )
    }
}
