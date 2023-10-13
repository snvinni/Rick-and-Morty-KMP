package feature.app

import core.util.navigation.Navigate
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

    fun onDetailsAction(onNavigate: Navigate) {
        when (onNavigate) {
            is Navigate.EpisodeDetails -> {
                navigateTo(
                    Screen.EpisodeDetails(onNavigate.episode)
                )
            }

            is Navigate.LocationDetails -> {
                navigateTo(
                    Screen.LocationDetails(onNavigate.location)
                )
            }

            is Navigate.CharacterDetails -> {
                navigateTo(
                    Screen.CharacterDetails(onNavigate.character)
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