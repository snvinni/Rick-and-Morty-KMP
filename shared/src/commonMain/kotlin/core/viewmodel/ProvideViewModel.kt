package core.viewmodel

import androidx.compose.runtime.Composable
import feature.app.AppViewModel
import feature.home.HomeViewModel

expect object ProvideViewModel {

    @Composable
    fun provideAppViewModel(): AppViewModel

    @Composable
    fun provideHomeViewModel(): HomeViewModel
}