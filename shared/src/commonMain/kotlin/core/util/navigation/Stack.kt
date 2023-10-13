package core.util.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Stack(
    private val stack : MutableList<Screen> = mutableListOf()
) {

    private val _hasBackStack = MutableStateFlow(stack.isNotEmpty())
    val hasBackStack = _hasBackStack.asStateFlow()

    fun set(value: Screen) {
        _hasBackStack.value = true
        stack.add(value)
    }
    
    fun pop(): Screen? {
        val screen = stack.removeLastOrNull()

        _hasBackStack.value = stack.isNotEmpty()

        return screen
    }
}