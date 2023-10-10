package feature.character.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.unit.dp
import core.component.ImageRequest
import domain.model.Character
import feature.paginator.LoadingType

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CharacterContent(
    modifier: Modifier = Modifier,
    onItemClick: (Int) -> Unit = {},
    characters: List<Character> = emptyList(),
    loadingType: LoadingType,
    loadMore: () -> Unit = {},
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colors.surface

    ) {
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(520.dp),
            modifier = modifier,
        ) {

            items(
                characters.size,
            ) { i ->
                val character = characters[i]

                if (i == characters.size - 1 && loadingType !is LoadingType.Done)
                    LaunchedEffect(key1 = true) {
                        loadMore()
                    }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable {
                            onItemClick(character.id)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    CharacterItem(
                        character = character,
                        onItemClick = onItemClick
                    )
                }

            }

            item {
                    run {
                        CircularProgressIndicator(
                            color = MaterialTheme.colors.primary,
                            modifier = Modifier
                                .scale(0.1f)
                                .padding(8.dp)
                        )
                }
            }
        }
    }
}

@Composable
fun CharacterItem(
    modifier: Modifier = Modifier,
    character: Character,
    onItemClick: (Int) -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier.padding(8.dp)
            .clickable {
                onItemClick(character.id)
            },
        elevation = 4.dp
    ) {
        Column(
            modifier = modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            ImageRequest(
                url = character.image,
                modifier = Modifier.size(100.dp).padding(4.dp),
            )

            Text(
                text = character.name,
                style = MaterialTheme.typography.h6
            )

            Text(
                text = character.species,
                style = MaterialTheme.typography.body2
            )
        }
    }
}