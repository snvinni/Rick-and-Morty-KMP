package core

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

actual open class BaseViewModel actual constructor() {
    actual val viewModelScope: CoroutineScope = MainScope()
}