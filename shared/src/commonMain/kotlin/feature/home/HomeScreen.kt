package feature.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import core.Resource

@Composable
fun HomeScreen(viewModel: HomeViewModel) {

    val resultState = viewModel.characters.collectAsState()

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        when(val result = resultState.value) {
            Resource.Loading -> {
                Text("loading...")
            }
            is Resource.Result.Failure -> {
                Text(result.error)
            }
            is Resource.Result.Success -> {
                LazyColumn {
                    items(result.data) {
                        Text(it.name)
                    }
                }
            }
        }
    }
}