package core.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import feature.app.AppViewModel
import feature.home.HomeViewModel

actual object ProvideViewModel {

    @Composable
    actual fun provideAppViewModel(): AppViewModel {
        return remember { AppViewModel() }
    }

    @Composable
    actual fun provideHomeViewModel(): HomeViewModel {
        return remember { HomeViewModel() }
    }
}