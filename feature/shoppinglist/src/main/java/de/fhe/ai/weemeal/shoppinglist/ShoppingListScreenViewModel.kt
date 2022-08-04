package de.fhe.ai.weemeal.shoppinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.domain.models.ShoppingList
import de.fhe.ai.weemeal.usecases.shoppingList.GetShoppingList
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShoppingListScreenViewModel(
    private val navigationManager: NavigationManager
) :
    ViewModel(),
    KoinComponent {

    private val getShoppingList: GetShoppingList by inject()

    var shoppingList: ShoppingList = ShoppingList(0, emptyList<Ingredient>())


    init {
        this.getShoppingListFromDb()
    }

    private fun getShoppingListFromDb() {

        viewModelScope.launch {
            shoppingList = getShoppingList.execute()!!
        }
    }


}