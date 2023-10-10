package core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope

actual open class BaseViewModel actual constructor() : ViewModel() {
    actual val scope: CoroutineScope get() = viewModelScope
}