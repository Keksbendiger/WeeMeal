package de.fhe.ai.weemeal.mocks.local

import de.fhe.ai.weemeal.common.extentions.measurementUnit
import de.fhe.ai.weemeal.domain.formats.QuantityFormat
import de.fhe.ai.weemeal.local.entity.IngredientEntity
import io.bloco.faker.Faker

object IngredientEntityMock {
    private val faker: Faker = Faker()

    fun generateSingleObject(
        id: Long = 0,
        name: String = faker.food.dish(),
        image: String? = "",
        quantity: QuantityFormat = QuantityFormat(
            quantity = faker.number.between(1, 100).toFloat(),
            unit = faker.measurementUnit()
        ),
    ): IngredientEntity {
        return IngredientEntity(
            id = id,
            name = name,
            image = image,
            quantity = quantity,
        )
    }

    fun generateList(
        amount: Int? = faker.number.between(1, 30)
    ): List<IngredientEntity> {

        val internalList: MutableList<IngredientEntity> = mutableListOf()

        for (i in 1..amount!!) {
            internalList.add(generateSingleObject())
        }
        return internalList
    }
}
