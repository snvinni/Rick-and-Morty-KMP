package feature.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.designSystem.SimpleBadge
import core.util.ImageRequest
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

    Box(Modifier.padding(16.dp)) {

        ImageRequest(
            character.imageUrl,
            Modifier.size(100.dp)
                .clip(CircleShape)
                .background(Color.Gray),
        )

        SimpleBadge(Modifier.align(Alignment.BottomEnd)) {
            Text(
                character.status,
                Modifier.padding(4.dp)
            )
        }
    }
}