package de.fhe.ai.weemeal.repository

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.fhe.ai.weemeal.local.WeeMealDatabase
import de.fhe.ai.weemeal.local.dao.IngredientEntityDao
import de.fhe.ai.weemeal.local.dao.MealEntityDao
import de.fhe.ai.weemeal.local.dao.RecipeEntityDao
import de.fhe.ai.weemeal.local.dao.RecipeIngredientEntityDao
import de.fhe.ai.weemeal.local.dao.RecipeTagEntityDao
import de.fhe.ai.weemeal.local.dao.TagEntityDao
import de.fhe.ai.weemeal.repository.ingredient.IngredientRepository
import de.fhe.ai.weemeal.repository.ingredient.IngredientRepositoryImpl
import de.fhe.ai.weemeal.repository.recipe.RecipeRepository
import de.fhe.ai.weemeal.repository.recipe.RecipeRepositoryImpl
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

@RunWith(AndroidJUnit4::class)
abstract class BaseTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        androidContext(ApplicationProvider.getApplicationContext())
        androidLogger()
        modules(
            module {
                single<RecipeRepository> {
                    RecipeRepositoryImpl(
                        recipeEntityDao = WeeMealDatabase.getRecipeEntityDao(get()),
                        ingredientEntityDao = WeeMealDatabase.getIngredientEntityDao(get()),
                        tagEntityDao = WeeMealDatabase.getTagEntityDao(get()),
                        recipeIngredientEntityDao = WeeMealDatabase.getRecipeIngredientEntityDao(get()),
                        recipeTagEntityDao = WeeMealDatabase.getRecipeTagEntityDao(get())
                    )
                }
//                single<IngredientRepository> {
//                    IngredientRepositoryImpl(
//                        WeeMealDatabase.getIngredientEntityDao(get()),
//                        WeeMealDatabase.getRecipeIngredientEntityDao(get())
//                    )
//                }
            }
        )
    }
}
