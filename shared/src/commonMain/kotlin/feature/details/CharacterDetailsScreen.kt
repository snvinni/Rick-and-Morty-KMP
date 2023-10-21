package feature.details

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import core.designSystem.BodyStyle
import core.designSystem.CharacterDetails
import core.designSystem.LinkColor
import core.designSystem.SubTitleStyle
import core.util.navigation.Navigate
import domain.model.Character

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CharacterDetailsScreen(
    character: Character,
    modifier: Modifier = Modifier,
    onNavigate: (Navigate) -> Unit = {},
) = Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally,
) {

    Spacer(Modifier.height(16.dp))

    CharacterDetails(character)

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
                character.origin,
                onNavigate
            )

            Location(
                type = "Actual",
                character.location,
                onNavigate
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
                            onNavigate(Navigate.EpisodeDetails(episode))
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
    action: (Navigate) -> Unit,
    modifier: Modifier = Modifier
) = Row(modifier) {

    Text("$type: ", style = BodyStyle)

    Text(
        location.name,
        Modifier.clickable {
            action(Navigate.LocationDetails(location))
        },
        style = BodyStyle.copy(color = LinkColor)
    )
}