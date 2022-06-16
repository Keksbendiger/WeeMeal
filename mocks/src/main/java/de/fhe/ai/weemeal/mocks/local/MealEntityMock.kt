package de.fhe.ai.weemeal.mocks.local

import de.fhe.ai.weemeal.domain.enums.CookColor
import de.fhe.ai.weemeal.local.entity.MealEntity
import io.bloco.faker.Faker
import java.util.Date

object MealEntityMock {
    private val faker: Faker = Faker()

    fun generateSingleObject(
        id: Long = 0,
        recipeId: Long = faker.number.between(1, 10).toLong(), // TODO: could cause an error
        servings: Int? = faker.number.between(1, 10),
        cookColor: CookColor? = CookColor.getRandom(),
        cookingDate: Date = faker.date.forward(),
        shoppingListCreatedAt: Date = faker.date.forward(),

    ): MealEntity {
        return MealEntity(
            id = id,
            recipeId = recipeId,
            servings = servings,
            cookColor = cookColor,
            cookingDate = cookingDate,
            shoppingListCreatedAt = shoppingListCreatedAt,
        )
    }

    fun generateList(amount: Int? = faker.number.between(1, 30)): List<MealEntity> {

        val internalList: MutableList<MealEntity> = mutableListOf()

        for (i in 1..amount!!) {
            internalList.add(generateSingleObject())
        }
        return internalList
    }
}
