package de.fhe.ai.weemeal.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import de.fhe.ai.weemeal.app.di.modules.initKoin
import de.fhe.ai.weemeal.app.ui.screens.core.AppScaffold
import de.fhe.ai.weemeal.common.theme.WeeMealTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterialApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initKoin {
            androidContext(this@MainActivity)
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
        }
        setContent {
//            Navigation()
            WeeMealTheme {
                Surface {
                    AppScaffold()
                }
            }
        }
    }
}