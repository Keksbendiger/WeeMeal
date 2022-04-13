package de.fhe.ai.weemeal.app.di.modules

import de.fhe.ai.weemeal.android_core.LoggerImpl
import de.fhe.ai.weemeal.data.AppDatabase
import de.fhe.ai.weemeal.data.RepositoryImpl
import de.fhe.ai.weemeal.data.UserEntityDao
import de.fhe.ai.weemeal.domain.Logger
import de.fhe.ai.weemeal.domain.Repository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single<AppDatabase> {
        AppDatabase.getDatabase(androidApplication())
    }

    single<UserEntityDao> {
        AppDatabase.getUserEntityDao(get())
    }

    single<Repository> {
        RepositoryImpl(get())
    }
}

val androidCoreModule = module {
    single<Logger> {
        LoggerImpl()
    }
}