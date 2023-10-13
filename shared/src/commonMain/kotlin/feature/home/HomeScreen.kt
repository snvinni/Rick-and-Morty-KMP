package feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.component.CharacterContent


@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    val uiState = viewModel.uiState.collectAsState().value

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CharacterContent(
            modifier = Modifier
                .fillMaxSize(),
            characters = uiState.characterList,
            onItemClick = viewModel::onItemClick,
            loadingType = uiState.loadingType,
            loadMore = viewModel::loadCharacters,
            refresh = viewModel::refresh
        )
    }
}
