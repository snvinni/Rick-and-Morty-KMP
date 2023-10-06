package core

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import androidx.lifecycle.viewModelScope as androidViewModelScope

actual open class BaseViewModel actual constructor() : ViewModel() {
    actual val viewModelScope: CoroutineScope get() = androidViewModelScope
}