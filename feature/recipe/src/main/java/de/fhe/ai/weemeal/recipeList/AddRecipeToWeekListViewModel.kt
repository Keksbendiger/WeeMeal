package de.fhe.ai.weemeal.recipeList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.navigation.Screen
import de.fhe.ai.weemeal.domain.models.Meal
import de.fhe.ai.weemeal.domain.models.Recipe
import de.fhe.ai.weemeal.usecases.meal.SaveMeal
import de.fhe.ai.weemeal.usecases.recipe.SearchRecipes
import java.util.Date
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AddRecipeToWeekListViewModel(
//    private val getRecipesAsync: GetRecipesAsync,
//    private val loadRecipesFromNetwork: LoadRecipesFromNetwork,
    private val cookingDate: String,
    private val navigationManager: NavigationManager
) : ViewModel(), KoinComponent {

    private val searchRecipes: SearchRecipes by inject()
    private val saveMeal: SaveMeal by inject()

    // See https://code.luasoftware.com/tutorials/android/jetpack-compose-load-data/
//    var dbOp by mutableStateOf(AsyncOperation.undefined())
//    var networkOp by mutableStateOf(AsyncOperation.undefined())
    var recipeList by mutableStateOf(emptyList<Recipe>())

    init {
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


    fun saveMealToCookingDate(recipe: Recipe){
        val meal = Meal(recipe = recipe, cookingDate = Date(this.cookingDate))
        viewModelScope.launch {
            saveMeal.execute(meal)
        }
        navigateToAddToWeekList()
    }

    private fun navigateToAddToWeekList() {
        navigationManager.navigate(Screen.WeekList.navigationCommand())
    }
}
