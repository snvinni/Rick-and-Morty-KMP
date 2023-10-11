package feature.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.designSystem.SimpleBadge
import core.designSystem.SubTitleStyle
import core.util.ImageRequest
import domain.model.Character

@Composable
fun DetailsScreen(
    character: Character,
    modifier: Modifier = Modifier,
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
) {

    Spacer(Modifier.height(16.dp))

    Box {
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

    Spacer(Modifier.height(16.dp))

    Column(
        Modifier.padding(horizontal = 16.dp),
        Arrangement.spacedBy(8.dp)
    ) {
        Card(Modifier.fillMaxWidth()) {

            Column(Modifier.padding(8.dp)) {
                Text("Basics", style = SubTitleStyle)

                Spacer(Modifier.height(4.dp))

                Text("Specie: ${character.species}")
                Text("Gender: ${character.gender}")
            }
        }
    }

    Spacer(Modifier.height(16.dp))
}