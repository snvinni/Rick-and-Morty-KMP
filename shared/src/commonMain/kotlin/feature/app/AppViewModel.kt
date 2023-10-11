package feature.app

import core.util.navigation.Action
import core.util.navigation.Screen
import core.util.navigation.Stack
import core.viewmodel.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppViewModel : BaseViewModel() {

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Home)
    val currentScreen = _currentScreen.asStateFlow()

    private val stack = Stack()
    val hasBackStack = stack.hasBackStack

    fun onDetailsAction(onAction: Action) {
        when (onAction) {
            is Action.OpenEpisode -> {
                navigateTo(
                    Screen.EpisodeDetails(onAction.episode)
                )
            }

            is Action.OpenLocation -> {
                navigateTo(
                    Screen.LocationDetails(onAction.location)
                )
            }

            is Action.OpenDetails -> {
                navigateTo(
                    Screen.CharacterDetails(onAction.character)
                )
            }
        }
    }

    private fun navigateTo(screen: Screen) {
        stack.set(currentScreen.value)
        _currentScreen.value = screen
    }

    fun onBack() {
        _currentScreen.value = stack.pop() ?: return
    }
}