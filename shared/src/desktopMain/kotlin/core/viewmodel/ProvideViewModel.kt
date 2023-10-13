package core.viewmodel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import feature.app.AppViewModel
import feature.home.HomeViewModel

actual object ProvideViewModel {

   @Composable
   actual inline fun <reified T: BaseViewModel> provide(): T {

        return when(T::class) {
            HomeViewModel::class -> remember(T::class) {  HomeViewModel() as T }
            AppViewModel::class -> remember(T::class) {  AppViewModel() as T }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}