package de.fhe.ai.weemeal.usecases.di

import de.fhe.ai.weemeal.usecases.meal.DeleteMeal
import de.fhe.ai.weemeal.usecases.meal.GetMealById
import de.fhe.ai.weemeal.usecases.meal.SaveMeal
import de.fhe.ai.weemeal.usecases.recipe.DeleteRecipe
import de.fhe.ai.weemeal.usecases.recipe.GetRecipeById
import de.fhe.ai.weemeal.usecases.recipe.GetRecipeByMeal
import de.fhe.ai.weemeal.usecases.recipe.GetRecipes
import de.fhe.ai.weemeal.usecases.recipe.SaveRecipe
import de.fhe.ai.weemeal.usecases.recipe.SearchRecipes
import de.fhe.ai.weemeal.usecases.usabilityTest.PrepareHCIUsabilityTest
import de.fhe.ai.weemeal.usecases.weekList.GetFutureMeals
import org.koin.dsl.module

val usecases = module {
    single { SearchRecipes() }
    single { SaveRecipe() }
    single { DeleteRecipe() }
    single { GetRecipes() }
    single { SaveMeal() }
    single { DeleteMeal() }
    single { GetFutureMeals() }

    single { GetRecipeByMeal() }
    single { GetRecipeById() }

    single { GetMealById() }

    single { PrepareHCIUsabilityTest() }
}
