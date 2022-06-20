package de.fhe.ai.weemeal.repository

import de.fhe.ai.weemeal.local.WeeMealDatabase
import de.fhe.ai.weemeal.repository.meal.MealRepository
import de.fhe.ai.weemeal.repository.meal.MealRepositoryImpl
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import de.fhe.ai.weemeal.repository.recipe.RecipeRepositoryImpl
import org.koin.dsl.module

val repository = module {
    single<RecipeRepository> {
        RecipeRepositoryImpl(
            recipeEntityDao = WeeMealDatabase.getRecipeEntityDao(get()),
            ingredientEntityDao = WeeMealDatabase.getIngredientEntityDao(get()),
            tagEntityDao = WeeMealDatabase.getTagEntityDao(get()),
            recipeIngredientEntityDao = WeeMealDatabase.getRecipeIngredientEntityDao(get()),
            recipeTagEntityDao = WeeMealDatabase.getRecipeTagEntityDao(get())
        )
    }

    single<MealRepository> {
        MealRepositoryImpl(
            mealEntityDao = WeeMealDatabase.getMealEntityDao(get())
        )
    }
}
