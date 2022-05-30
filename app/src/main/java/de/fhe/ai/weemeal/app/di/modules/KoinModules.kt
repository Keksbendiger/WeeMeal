package de.fhe.ai.weemeal.app.di.modules

import de.fhe.ai.weemeal.common.logger.Logger
import de.fhe.ai.weemeal.common.logger.LoggerImpl
import de.fhe.ai.weemeal.local.WeeMealDatabase
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
//            viewModelModule
        )
    }

val androidCoreModule = module {
    single<Logger> {
        LoggerImpl()
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

// val viewModelModule = module {
//    viewModel { MainScreenViewModel(get()) }
// }
