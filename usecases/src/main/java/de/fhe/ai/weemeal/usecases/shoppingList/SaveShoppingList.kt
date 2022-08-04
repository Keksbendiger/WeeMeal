package de.fhe.ai.weemeal.usecases.shoppingList

import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.domain.models.ShoppingList
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import de.fhe.ai.weemeal.repository.shoppingList.ShoppingListRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SaveShoppingList : KoinComponent {

    private val shoppingListRepository: ShoppingListRepository by inject()


    suspend fun execute(shoppingList: ShoppingList) {
        shoppingListRepository.insertShoppingList(shoppingList)
    }
}
