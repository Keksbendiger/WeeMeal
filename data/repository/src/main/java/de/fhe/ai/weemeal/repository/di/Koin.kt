package de.fhe.ai.weemeal.repository.di

import de.fhe.ai.weemeal.local.WeeMealDatabase
import de.fhe.ai.weemeal.local.dao.IngredientEntityDao
import de.fhe.ai.weemeal.local.dao.ShoppingListEntityDao
import de.fhe.ai.weemeal.repository.meal.MealRepository
import de.fhe.ai.weemeal.repository.meal.MealRepositoryImpl
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import de.fhe.ai.weemeal.repository.recipe.RecipeRepositoryImpl
import de.fhe.ai.weemeal.repository.shoppingList.ShoppingListRepository
import de.fhe.ai.weemeal.repository.shoppingList.ShoppingListRepositoryImpl
import org.koin.dsl.module

/**
 * dependency injection for the repository layer
 */
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

    single<ShoppingListRepository> {
        ShoppingListRepositoryImpl(
            shoppingListEntityDao = WeeMealDatabase.getShoppingListEntityDao(get()),
            ingredientEntityDao = WeeMealDatabase.getIngredientEntityDao(get()),
        )
    }


}
