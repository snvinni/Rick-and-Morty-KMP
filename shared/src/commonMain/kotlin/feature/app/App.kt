package feature.app

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import core.util.navigation.Screen
import core.viewmodel.ProvideViewModel
import feature.details.CharacterDetailsScreen
import feature.home.HomeScreen

@Composable
fun App(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = ProvideViewModel.provide()
) = Column(modifier = modifier) {

    val hasBackStack = viewModel.hasBackStack.collectAsState(false).value
    val screen = viewModel.currentScreen.collectAsState().value

    if (hasBackStack) {
        TopAppBar(
            title = {
                Text(screen.name)
            },
            navigationIcon = {
                IconButton(onClick = viewModel::onBack) {
                    Icon(
                        Icons.TwoTone.ArrowBack,
                        contentDescription = null
                    )
                }
            }
        )
    } else {
        TopAppBar(
            title = {
                Text(screen.name)
            },
            navigationIcon = null
        )
    }

    when (screen) {
        is Screen.Home -> HomeScreen(
            onAction = viewModel::onDetailsAction,
        )

        is Screen.CharacterDetails -> {
            CharacterDetailsScreen(
                character = screen.character,
                modifier = Modifier.fillMaxSize()
            )
        }

        is Screen.EpisodeDetails -> TODO()

        is Screen.LocationDetails -> TODO()
    }
}