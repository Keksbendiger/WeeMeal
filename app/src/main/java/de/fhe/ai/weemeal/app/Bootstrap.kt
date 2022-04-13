package de.fhe.ai.weemeal.app

import android.app.Application
import de.fhe.ai.weemeal.app.di.modules.androidCoreModule
import de.fhe.ai.weemeal.app.di.modules.databaseModule
import de.fhe.ai.weemeal.domain.Logger
import de.fhe.ai.weemeal.domain.Repository
import de.fhe.ai.weemeal.domain.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Bootstrap : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@Bootstrap)
            modules(databaseModule)
            modules(androidCoreModule)
        }

        DbTest().run()
    }
}

class DbTest: KoinComponent {
    private val repo by inject<Repository>()
    private val logger by inject<Logger>()

    fun run() {
        val dbScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

        dbScope.launch {
            val newUserId = repo.insertUser( User( name = "Steff") )
            logger.info( "${repo.getUser(newUserId)}" )
        }
    }
}