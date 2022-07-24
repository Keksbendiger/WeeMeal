package de.fhe.ai.weemeal.app.di.modules

import de.fhe.ai.weemeal.common.logger.Logger
import de.fhe.ai.weemeal.common.logger.LoggerImpl
import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.local.WeeMealDatabase
import de.fhe.ai.weemeal.mealDetail.MealDetailsViewModel
import de.fhe.ai.weemeal.recipeDetail.RecipeDetailsViewModel
import de.fhe.ai.weemeal.recipeDetail.RecipeEditViewModel
import de.fhe.ai.weemeal.recipeList.RecipeListViewModel
import de.fhe.ai.weemeal.repository.di.repository
import de.fhe.ai.weemeal.shoppinglist.ShoppingListSelectScreenViewModel
import de.fhe.ai.weemeal.usecases.di.usecases
import de.fhe.ai.weemeal.weeklistComponent.WeekListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(
            androidCoreModule,
            databaseModule,
            usecases,
            viewModelModule,
            repository
        )
    }

val androidCoreModule = module {
    single<Logger> {
        LoggerImpl()
    }
    single {
        NavigationManager()
    }
}

val databaseModule = module {

    single { WeeMealDatabase.getRecipeEntityDao(get()) }
//    single {
//        RecipeRepositoryImpl(
//            WeeMealDatabase.getRecipeEntityDao(get())
//        )
//    }
}

// val useCaseModule = module {
//    factory { GetUsers(get()) }
// }

val viewModelModule = module {
    viewModel { MealDetailsViewModel(get(), get()) }
    viewModel { RecipeEditViewModel(get(), get()) }
    viewModel { RecipeDetailsViewModel(get(), get()) }
    viewModel { RecipeListViewModel(get()) }
    viewModel { WeekListViewModel(get()) }
    viewModel { ShoppingListSelectScreenViewModel(get()) }

//    TODO: Add all used viewmodels here
}
