package de.fhe.ai.weemeal.android_core

import de.fhe.ai.weemeal.BuildConfig
import de.fhe.ai.weemeal.domain.Logger
import timber.log.Timber

class LoggerImpl : Logger {

    companion object {
        init {
            if (BuildConfig.DEBUG) {
                Timber.plant(Timber.DebugTree())
                Timber.i("Logger initialized")
            }
        }
    }

    override fun error(message: String) {
        Timber.tag(callingCallName()).e( message )
    }

    override fun info(message: String) {
        Timber.tag(callingCallName()).i( message )
    }

    private fun callingCallName(): String? {
        val fullClassName = Thread.currentThread().stackTrace[4]?.className
        return fullClassName?.substring(fullClassName.lastIndexOf('.'))
    }

}
