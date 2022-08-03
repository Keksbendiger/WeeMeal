package de.fhe.ai.weemeal.mocks.local

import de.fhe.ai.weemeal.common.extentions.timeUnit
import de.fhe.ai.weemeal.domain.R
import de.fhe.ai.weemeal.domain.formats.TimeFormat
import de.fhe.ai.weemeal.local.entity.RecipeEntity
import de.fhe.ai.weemeal.local.entity.ShoppingListEntity
import io.bloco.faker.Faker

object ShoppingListEntityMock {
    private val faker: Faker = Faker()

    fun generateSingleObject(
        id: Long = 0,
        shoppingListId: Long = 0,
        ingredientId: Long = 0,
    ): ShoppingListEntity {
        return ShoppingListEntity(
            id = id,
            shoppingListId = shoppingListId,
            ingredientId = ingredientId,
        )
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<ShoppingListEntity> {

        val internalList: MutableList<ShoppingListEntity> = mutableListOf()

        for (i in 1..amount!!) {
            internalList.add(generateSingleObject(ingredientId = i.toLong()))
        }
        return internalList
    }
}
