package de.fhe.ai.weemeal.app.di.modules

import de.fhe.ai.weemeal.common.navigation.NavigationManager
import de.fhe.ai.weemeal.common.logger.Logger
import de.fhe.ai.weemeal.common.logger.LoggerImpl
import de.fhe.ai.weemeal.local.WeeMealDatabase
import de.fhe.ai.weemeal.mealDetail.MealDetailsViewModel
import de.fhe.ai.weemeal.recipeDetail.RecipeEditViewModel
import de.fhe.ai.weemeal.recipeList.RecipeListViewModel
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
//            useCaseModule,
            viewModelModule
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
    viewModel { MealDetailsViewModel() }
    viewModel { RecipeEditViewModel() }
    viewModel { RecipeListViewModel(get()) }
//    TODO: Add all used viewmodels here
}
