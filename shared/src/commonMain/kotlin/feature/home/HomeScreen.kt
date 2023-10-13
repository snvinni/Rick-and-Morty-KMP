package feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.designSystem.PaginationLoading
import core.theme.Green
import core.util.navigation.Navigate
import core.viewmodel.ProvideViewModel
import feature.paginator.LoadingType


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = ProvideViewModel.provide(),
    onAction: (Navigate) -> Unit = {}
) = Box(
    contentAlignment = Alignment.Center,
    modifier = modifier
) {

    val (characters, loadingType) = viewModel.uiState.collectAsState().value

    when {
        loadingType is LoadingType.FirstPage && characters.isEmpty() -> {
            PaginationLoading(loadingType)
        }

        loadingType is LoadingType.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                OutlinedButton(
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Green
                    ),
                    onClick = { viewModel.refresh() },
                ) {
                    Text("Retry")
                }
            }
        }

        else -> Characters(
            modifier = Modifier.fillMaxSize(),
            characters = characters,
            onItemClick = {
                onAction(Navigate.CharacterDetails(it))
            },
            loadingType = loadingType,
            loadMore = viewModel::loadCharacters,
        )
    }
}
