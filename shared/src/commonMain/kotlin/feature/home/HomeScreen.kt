package feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.util.navigation.Navigate
import core.viewmodel.ProvideViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = ProvideViewModel.provide(),
    modifier: Modifier = Modifier,
    onAction: (Navigate) -> Unit
) = Box(
    contentAlignment = Alignment.Center,
    modifier = modifier.fillMaxSize()
) {

    val uiState = viewModel.uiState.collectAsState().value

    CharacterContent(
        modifier = Modifier.fillMaxSize(),
        characters = uiState.characterList,
        onItemClick = {
            onAction(Navigate.CharacterDetails(it))
        },
        loadingType = uiState.loadingType,
        loadMore = viewModel::loadCharacters,
        refresh = viewModel::refresh
    )
}
