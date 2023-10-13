package core.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

actual object ProvideViewModel {

    @Composable
    actual inline fun <reified T: BaseViewModel> provide(): T = viewModel()
}