package de.fhe.ai.weemeal.app.di.modules

import de.fhe.ai.weemeal.common.logger.Logger
import de.fhe.ai.weemeal.common.logger.LoggerImpl
import org.koin.dsl.module

val databaseModule = module {
//    single<AppDatabase> {
//        AppDatabase.getDatabase(androidApplication())
//    }
//
//    single<UserEntityDao> {
//        AppDatabase.getUserEntityDao(get())
//    }
//
//    single<Repository> {
//        RepositoryImpl(get())
//    }
}

val androidCoreModule = module {
    single<Logger> {
        LoggerImpl()
    }
}
