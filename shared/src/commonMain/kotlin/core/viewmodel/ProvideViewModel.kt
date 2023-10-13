package core.viewmodel

import androidx.compose.runtime.Composable

expect object ProvideViewModel {
    @Composable
    inline fun <reified T: BaseViewModel> provide(): T
}