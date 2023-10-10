package feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import feature.character.components.CharacterContent


@Composable
fun HomeScreen(viewModel: HomeViewModel) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        CharacterContent(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            characters = viewModel.characterListFlow.collectAsState().value,
            onItemClick = viewModel::onItemClick,
            loadingType = viewModel.loadingType.collectAsState().value,
            loadMore = viewModel::loadCharacters
        )

    }
}
