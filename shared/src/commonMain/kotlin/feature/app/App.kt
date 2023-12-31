package feature.app

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import core.util.Platform
import core.util.getPlatform
import core.util.navigation.Screen
import core.viewmodel.ProvideViewModel
import feature.details.CharacterDetailsScreen
import feature.home.HomeScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun App(
    modifier: Modifier = Modifier,
    viewModel: AppViewModel = ProvideViewModel.provideAppViewModel()
) = Column(modifier = modifier) {

    val hasBackStack = viewModel.hasBackStack.collectAsState(false).value
    val screen = viewModel.currentScreen.collectAsState().value

    if (getPlatform() != Platform.WASM) {
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
    }

    val charactersState = rememberLazyStaggeredGridState()

    when (screen) {
        is Screen.Home -> HomeScreen(
            onAction = viewModel::onNavigate,
            charactersState = charactersState,
            modifier = Modifier.fillMaxSize()
        )

        is Screen.CharacterDetails -> {
            CharacterDetailsScreen(
                character = screen.character,
                onNavigate = viewModel::onNavigate,
                modifier = Modifier.fillMaxSize()
            )
        }

        is Screen.EpisodeDetails -> TODO()

        is Screen.LocationDetails -> TODO()
    }
}