package feature.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun provideHomeViewModel() = remember { HomeViewModel()}