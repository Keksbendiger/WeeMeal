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

    override suspend fun getShoppingList(): ShoppingList? {
        Timber.i("Get ShoppingList from database by ID")
        val internalShoppingList: MutableList<Ingredient> = mutableListOf()

        shoppingListEntityDao.getAll().let { shoppingListEntityList ->
            shoppingListEntityList.forEach { shoppingListEntity ->

                ingredientEntityDao.get(shoppingListEntity.ingredientId)?.let { ingredientEntity ->
                    internalShoppingList.add(
                        ingredientEntity.toDomain()
                    )
                }
            }


        }
        if (internalShoppingList.isNotEmpty()) {
            return ShoppingList(
                items = internalShoppingList
            )
        } else {
            return null
        }

    }

    override suspend fun insertShoppingList(shoppingList: ShoppingList): Long {
        shoppingListEntityDao.deleteAll()
        shoppingList.items.forEach { ingredient ->
            shoppingListEntityDao.insert(
                ShoppingListEntity(
                    ingredientId = ingredient.internalId
                )
            )
        }
        return shoppingList.internalId
    }

    override suspend fun deleteShoppingList() {
        shoppingListEntityDao.deleteAll()
    }

    /*override suspend fun getShoppingListByID(shoppingListId: Long): ShoppingListEntity {

        val shoppingList = shoppingListEntityDao.get(shoppingListId)

        return shoppingList
    }*/
}
