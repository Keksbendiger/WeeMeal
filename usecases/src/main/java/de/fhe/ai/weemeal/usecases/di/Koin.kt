package de.fhe.ai.weemeal.usecases.di

import de.fhe.ai.weemeal.usecases.recipe.SearchRecipes
import org.koin.dsl.module

val usecases = module {
    single { SearchRecipes() }
}
