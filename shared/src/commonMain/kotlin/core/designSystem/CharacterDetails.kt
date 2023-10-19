package core.designSystem

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import core.util.ImageRequest
import domain.model.Character

@Composable
fun CharacterDetails(
    character: Character,
    modifier: Modifier = Modifier
) = Box(modifier) {
    ImageRequest(
        character.imageUrl,
        Modifier.size(100.dp)
            .clip(CircleShape)
            .background(Color.Gray),
    )

    val statusColor = when (character.status) {
        "Alive" -> Color.Green
        "Dead" -> Color.Red
        else -> null
    }

    if (statusColor != null) {
        SimpleBadge(Modifier.align(Alignment.BottomEnd)) {

            Row(verticalAlignment = Alignment.CenterVertically) {

                Spacer(Modifier.width(4.dp))

                Box(
                    Modifier
                        .size(10.dp)
                        .background(
                            color = statusColor,
                            shape = CircleShape
                        )
                )

                Text(
                    character.status,
                    Modifier.padding(4.dp)
                )
            }
        }
    }
}