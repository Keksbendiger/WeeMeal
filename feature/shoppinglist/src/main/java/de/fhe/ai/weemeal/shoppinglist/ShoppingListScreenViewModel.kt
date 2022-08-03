package de.fhe.ai.weemeal.shoppinglist

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.domain.models.Ingredient
import de.fhe.ai.weemeal.usecases.meal.GetMealById
import de.fhe.ai.weemeal.usecases.shoppingList.GetShoppingList
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ShoppingListScreenViewModel(
    private val navigationManager: NavigationManager
) :
    ViewModel(),
    KoinComponent {

    //private val getMeal: GetMealById by inject()
    //private val getShoppingList: GetShoppingList by inject()


    var ingredientsList by mutableStateOf(emptyList<Ingredient>())

    /*init {
        this.getIngredientsFromDb()
    }*/

    /*private fun getIngredientsFromDb() {

        viewModelScope.launch {
            val shoppingList = getShoppingList.execute()
        }
    }*/


}