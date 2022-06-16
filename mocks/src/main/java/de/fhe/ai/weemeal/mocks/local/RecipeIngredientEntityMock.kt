package de.fhe.ai.weemeal.mocks.local

import de.fhe.ai.weemeal.common.extentions.measurementUnit
import de.fhe.ai.weemeal.domain.formats.QuantityFormat
import de.fhe.ai.weemeal.local.entity.IngredientEntity
import de.fhe.ai.weemeal.local.entity.RecipeIngredientEntity
import de.fhe.ai.weemeal.mocks.domain.IngredientMock
import io.bloco.faker.Faker

object RecipeIngredientEntityMock {
    private val faker: Faker = Faker()


    fun generateSingleObject(
        id: Long = 0,
        ingredientId: Long = 0,
        recipeId: Long = 0,
    ): RecipeIngredientEntity {
        return RecipeIngredientEntity(
            id = id,
            ingredientId = ingredientId,
            recipeId = recipeId,
        )
    }


    fun generateList(
        ingredientList: List<IngredientEntity> = IngredientEntityMock.generateList(),
        recipeId: Long
    ): List<RecipeIngredientEntity> {

        val internalList: MutableList<RecipeIngredientEntity> = mutableListOf()
        ingredientList.forEach { ingredient ->

            internalList.add(
                generateSingleObject(
                    ingredientId = ingredient.id,
                    recipeId = recipeId
                )
            )
        }
        return internalList
    }
}