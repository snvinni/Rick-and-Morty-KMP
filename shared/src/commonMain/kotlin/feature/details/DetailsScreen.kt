package feature.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.component.ImageRequest
import domain.model.Character

@Composable
fun DetailsScreen(
    character: Character,
    onBackPressed: () -> Unit = {},
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
) {

    TopAppBar(
        title = {
            Text(
                text = character.name
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackPressed
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )

    ImageRequest(
        url = character.imageUrl,
        shape = CircleShape,
        modifier = Modifier
            .padding(16.dp)
            .size(100.dp)
    )
}