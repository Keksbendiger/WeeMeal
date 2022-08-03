package de.fhe.ai.weemeal.repository.shoppingList

import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.domain.models.ShoppingList
import de.fhe.ai.weemeal.local.entity.ShoppingListEntity
import kotlinx.coroutines.flow.Flow

interface ShoppingListRepository {
    suspend fun getShoppingListByID(shoppingListId: Long): ShoppingListEntity
    suspend fun getShoppingList(): ShoppingList?
    suspend fun insertShoppingList(shoppingList: ShoppingList) : Long
    suspend fun deleteShoppingList()
}
