package core.viewmodel

import androidx.compose.runtime.Composable
import feature.app.AppViewModel
import feature.home.HomeViewModel

actual object ProvideViewModel {

    private val appViewModel by lazy { AppViewModel() }

    private val homeViewModel by lazy { HomeViewModel() }

    @Composable
    actual fun provideAppViewModel(): AppViewModel {
        return appViewModel
    }

    @Composable
    actual fun provideHomeViewModel(): HomeViewModel {
        return homeViewModel
    }
}