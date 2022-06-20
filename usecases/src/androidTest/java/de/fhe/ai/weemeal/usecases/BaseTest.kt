package de.fhe.ai.weemeal.usecases

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import de.fhe.ai.weemeal.repository.di.repository
import de.fhe.ai.weemeal.usecases.di.usecases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule

@RunWith(AndroidJUnit4::class)
abstract class BaseTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        androidContext(ApplicationProvider.getApplicationContext())
        androidLogger()
        modules(repository, usecases)
    }

    fun <T> runTest(
        block: suspend CoroutineScope.() -> T
    ) {
        runBlocking { block() }
    }
}
