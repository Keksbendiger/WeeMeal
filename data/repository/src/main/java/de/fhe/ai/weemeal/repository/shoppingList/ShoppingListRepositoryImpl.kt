package de.fhe.ai.weemeal.repository.shoppingList

import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.ShoppingList
import de.fhe.ai.weemeal.local.dao.IngredientEntityDao
import de.fhe.ai.weemeal.local.dao.ShoppingListEntityDao
import de.fhe.ai.weemeal.local.entity.ShoppingListEntity
import de.fhe.ai.weemeal.local.mapper.toDomain
import timber.log.Timber

class ShoppingListRepositoryImpl(
    private val shoppingListEntityDao: ShoppingListEntityDao,
    private val ingredientEntityDao: IngredientEntityDao,
) : ShoppingListRepository {

    override suspend fun getShoppingList(shoppingListId: Long): ShoppingList {
        Timber.i("Get ShoppingList from database by ID")
        val internalShoppingList: MutableList<Ingredient> = mutableListOf()

        shoppingListEntityDao.getAllByShoppingListId(shoppingListId)
            .forEach { shoppingListEntity ->
                ingredientEntityDao.get(shoppingListEntity.ingredientId)?.let {
                    internalShoppingList.add(
                        it.toDomain()
                    )
                }
            }

        return ShoppingList(
            internalId = shoppingListId,
            items = internalShoppingList
        )

    }

    override suspend fun insertShoppingList(shoppingList: ShoppingList): Long {
        shoppingList.items.forEach { ingredient ->
            shoppingListEntityDao.insert(
                ShoppingListEntity(
                    shoppingListId = shoppingList.internalId,
                    ingredientId = ingredient.internalId
                )
            )
        }

        return shoppingList.internalId
    }

    override suspend fun deleteShoppingList(shoppingList: ShoppingList) {
        shoppingListEntityDao.deleteAll()
    }
}
