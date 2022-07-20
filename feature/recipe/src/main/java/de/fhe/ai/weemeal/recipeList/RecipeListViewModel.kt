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
import de.fhe.ai.weemeal.usecases.recipe.SaveRecipe
import de.fhe.ai.weemeal.usecases.recipe.SearchRecipes
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class RecipeListViewModel(
//    private val getRecipesAsync: GetRecipesAsync,
//    private val loadRecipesFromNetwork: LoadRecipesFromNetwork,
    private val navigationManager: NavigationManager
) : ViewModel(), KoinComponent {

    private val searchRecipes: SearchRecipes by inject()
    private val saveRecipe: SaveRecipe by inject()

    // See https://code.luasoftware.com/tutorials/android/jetpack-compose-load-data/
//    var dbOp by mutableStateOf(AsyncOperation.undefined())
//    var networkOp by mutableStateOf(AsyncOperation.undefined())
    var recipeList by mutableStateOf(emptyList<Recipe>())

    init {
        val mockRecipes = RecipeMock.generateList()
        viewModelScope.launch {
            for (mockRecipe in mockRecipes) {
                saveRecipe.execute(mockRecipe)
            }
        }
        this.getRecipesFromDb()
    }

    private fun getRecipesFromDb() {
        viewModelScope.launch {
            recipeList = searchRecipes.execute("")
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

    fun navigateToAddRecipe() {
        navigationManager.navigate(Screen.RecipeEdit.navigationCommand())
    }

    fun navigateToAddToWeekList() {
        // TODO: Give recipeId and add it to Today
        navigationManager.navigate(Screen.WeekList.navigationCommand())
    }

    fun navigateToRecipeDetail(recipeId: Long) {
        navigationManager.navigate(Screen.RecipeDetail.navigationCommand(recipeId))
    }

    fun navigateToRecipeEdit(recipeId: Long) {
        navigationManager.navigate(Screen.RecipeEdit.navigationCommand(recipeId))
    }
}
