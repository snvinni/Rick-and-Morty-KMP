package feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import core.designSystem.CharacterDetails
import core.designSystem.PaginationLoading
import domain.model.Character
import feature.paginator.LoadingType
import kotlinx.collections.immutable.ImmutableList

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Characters(
    characters: ImmutableList<Character>,
    modifier: Modifier = Modifier,
    state: LazyStaggeredGridState = rememberLazyStaggeredGridState(),
    onItemClick: (Character) -> Unit = {},
    loadingType: LoadingType,
    loadMore: () -> Unit = {}
) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Adaptive(190.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        state = state
    ) {

        item(span = StaggeredGridItemSpan.FullLine) {
            Spacer(Modifier.height(4.dp))
        }

        items(characters) { character ->

            CharacterItem(
                character = character,
                onItemClick = onItemClick,
                modifier = Modifier.padding(8.dp)
            )
        }

        item(span = StaggeredGridItemSpan.FullLine) {

            Spacer(Modifier.size(8.dp))

            PaginationLoading(
                loadingType,
                Modifier.padding(16.dp)
            )

            if (loadingType is LoadingType.FirstPage || loadingType is LoadingType.NextPage) {
                LaunchedEffect(Unit) {
                    loadMore()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier,
    onItemClick: (Character) -> Unit = {}
) = Card(
    backgroundColor = Color.White,
    elevation = 8.dp,
    shape = RoundedCornerShape(8.dp),
    modifier = modifier,
    onClick = {
        onItemClick(character)
    }
) {
    Column(
        modifier = modifier.padding(12.dp),
        horizontalAlignment = CenterHorizontally
    ) {

        CharacterDetails(character)

        Spacer(Modifier.height(8.dp))

        Text(
            text = character.name,
            style = MaterialTheme.typography.h6
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(text = character.species, fontSize = 12.sp)
    }
}