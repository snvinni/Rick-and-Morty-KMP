package core.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

actual open class BaseViewModel actual constructor() {
    actual val scope: CoroutineScope = MainScope()
}