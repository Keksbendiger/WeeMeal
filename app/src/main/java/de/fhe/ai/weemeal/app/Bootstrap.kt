package de.fhe.ai.weemeal.app

import android.app.Application
import org.koin.core.context.startKoin

class Bootstrap : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
//            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
//            androidContext(this@Bootstrap)
//            modules(databaseModule)
//            modules(androidCoreModule)
//        }

//        DbTest().run()
        }
    }

// class DbTest : KoinComponent {
//    private val repo by inject<Repository>()
//    private val logger by inject<Logger>()
//
//    fun run() {
//        val dbScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
//
//        dbScope.launch {
//            val newUserId = repo.insertUser(User(name = "Steff"))
//            logger.info("${repo.getUser(newUserId)}")
//        }
//    }
}
