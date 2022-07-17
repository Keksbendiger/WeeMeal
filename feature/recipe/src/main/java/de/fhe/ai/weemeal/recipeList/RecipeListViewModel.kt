package de.fhe.ai.weemeal.recipeList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.navigation.Screen
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.mocks.RecipeMock
import kotlinx.coroutines.launch

class RecipeListViewModel(
//    private val getRecipesAsync: GetRecipesAsync,
//    private val loadRecipesFromNetwork: LoadRecipesFromNetwork,
    private val navigationManager: NavigationManager
) : ViewModel() {

    // See https://code.luasoftware.com/tutorials/android/jetpack-compose-load-data/
//    var dbOp by mutableStateOf(AsyncOperation.undefined())
//    var networkOp by mutableStateOf(AsyncOperation.undefined())
    var recipeList by mutableStateOf(emptyList<Recipe>())


    init {
        this.getRecipesFromDb()
    }

    private fun getRecipesFromDb() {
        viewModelScope.launch {
            recipeList = RecipeMock.generateList()
        }
    }
//    private fun getRecipesFromDb() {
//        viewModelScope.launch {
//            getRecipesAsync().collect {
//                dbOp = it
//                if( it.status == AsyncOperationState.SUCCESS )
//                    recipeList = it.payload as List<Recipe>
//            }
//        }
//    }

//    fun getRecipesFromNetwork() {
//        viewModelScope.launch {
//            loadRecipesFromNetwork().collect {
//                networkOp = it
//            }
//        }
//    }

//    fun navigateToAddRecipe() {
//        navigationManager.navigate(Screen.RecipeEdit.navigationCommand())
//    }
//
//    fun navigateToUser(recipeId: Long) {
//        navigationManager.navigate(Screen.RecipeDetail.navigationCommand(recipeId))
//    }
}
