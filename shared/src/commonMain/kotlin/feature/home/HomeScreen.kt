package feature.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.util.ImageRequest
import core.util.Resource
import core.util.navigation.Action

@Composable
fun HomeScreen(
    onAction: (Action) -> Unit,
    viewModel: HomeViewModel = HomeViewModel()
) {

    val resultState = viewModel.characters.collectAsState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when (val result = resultState.value) {
            Resource.Loading -> {
                Text("loading...")
            }

            is Resource.Result.Failure -> {
                Text(result.error)
            }

            is Resource.Result.Success -> {
                BoxWithConstraints {
                    val size = maxWidth / 3

                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(size)
                    ) {

                        items(result.data) {
                            ImageRequest(
                                url = it.imageUrl,
                                modifier = Modifier
                                    .size(size)
                                    .padding(4.dp).clickable {
                                        onAction(Action.OpenDetails(it))
                                    },
                            )
                        }
                    }
                }
            }
        }
    }
}