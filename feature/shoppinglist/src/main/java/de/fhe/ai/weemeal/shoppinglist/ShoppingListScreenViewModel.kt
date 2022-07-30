package de.fhe.ai.weemeal.shoppinglist

import androidx.lifecycle.ViewModel
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import org.koin.core.component.KoinComponent

class ShoppingListScreenViewModel(private val navigationManager: NavigationManager) :
    ViewModel(),
    KoinComponent {
}