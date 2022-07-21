package de.fhe.ai.weemeal.usecases.di

import de.fhe.ai.weemeal.usecases.meal.GetMealById
import de.fhe.ai.weemeal.usecases.meal.SaveMeal
import de.fhe.ai.weemeal.usecases.recipe.GetRecipeByMeal
import de.fhe.ai.weemeal.usecases.recipe.GetRecipes
import de.fhe.ai.weemeal.usecases.recipe.GetRecipeById
import de.fhe.ai.weemeal.usecases.recipe.SaveRecipe
import de.fhe.ai.weemeal.usecases.recipe.SearchRecipes
import org.koin.dsl.module

val usecases = module {
    single { SearchRecipes() }
    single { SaveRecipe() }
    single { GetRecipes() }

    single { GetRecipeByMeal() }
    single { GetRecipeById() }

    single { SaveMeal() }
    single { GetMealById() }

}
