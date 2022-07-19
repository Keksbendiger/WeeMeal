package de.fhe.ai.weemeal.usecases.di

import de.fhe.ai.weemeal.usecases.recipe.GetRecipes
import de.fhe.ai.weemeal.usecases.recipe.SaveRecipe
import de.fhe.ai.weemeal.usecases.recipe.SearchRecipes
import de.fhe.ai.weemeal.usecases.weekList.GetFutureMeals
import de.fhe.ai.weemeal.usecases.weekList.SaveMeal
import org.koin.dsl.module

val usecases = module {
    single { SearchRecipes() }
    single { SaveRecipe() }
    single { GetRecipes() }
    single { SaveMeal() }
    single { GetFutureMeals() }
}
