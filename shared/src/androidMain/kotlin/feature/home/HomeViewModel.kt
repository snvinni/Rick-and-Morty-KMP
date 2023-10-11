package feature.home

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
actual fun provideHomeViewModel() = viewModel<HomeViewModel>()