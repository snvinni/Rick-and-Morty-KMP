package feature.details

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.designSystem.BodyStyle
import core.designSystem.LinkColor
import core.designSystem.SimpleBadge
import core.designSystem.SubTitleStyle
import core.util.ImageRequest
import domain.model.Character

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    character: Character,
    modifier: Modifier = Modifier,
    action: (DetailsAction) -> Unit = {},
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

        Section(title = "Basics", Modifier.fillMaxWidth()) {

            Text("Specie: ${character.species}")
            Text("Gender: ${character.gender}")
        }

        Section(title = "Locations", Modifier.fillMaxWidth()) {
            Location(
                type = "Origin",
                character.location,
                action
            )

            Location(
                type = "Actual",
                character.location,
                action
            )
        }

        Section(
            title = "Episodes",
            Modifier.fillMaxWidth(),
        ) {
            LazyRow {
                items(character.episodes) { episode ->
                    Card(
                        shape = CircleShape,
                        modifier = Modifier.size(28.dp),
                        onClick = {
                            action(
                                DetailsAction.OpenEpisode(
                                    episode.url
                                )
                            )
                        }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(
                                episode.number.toString(),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Spacer(Modifier.width(4.dp))
                }
            }
        }
    }

    Spacer(Modifier.height(16.dp))
}

@Composable
fun Section(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) = Card(modifier) {

    Column(Modifier.padding(8.dp)) {

        Text(title, style = SubTitleStyle)

        Spacer(Modifier.height(8.dp))

        content()
    }
}

@Composable
fun Location(
    type: String,
    location: Character.Location,
    action: (DetailsAction) -> Unit,
    modifier: Modifier = Modifier
) = Row(modifier) {

    Text("$type: ", style = BodyStyle)

    Text(
        location.name,
        Modifier.clickable {
            action(
                DetailsAction.OpenLocation(
                    location.url
                )
            )
        },
        style = BodyStyle.copy(color = LinkColor)
    )
}