package core.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import feature.app.AppViewModel
import feature.home.HomeViewModel

actual object ProvideViewModel {

    @Composable
    actual fun provideAppViewModel(): AppViewModel = viewModel()

    @Composable
    actual fun provideHomeViewModel(): HomeViewModel =  viewModel()
}